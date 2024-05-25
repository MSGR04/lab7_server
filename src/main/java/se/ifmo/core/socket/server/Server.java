package se.ifmo.core.socket.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.ifmo.core.collection.model.User;
import se.ifmo.core.collection.service.UserService;
import se.ifmo.core.transfer.Request;
import se.ifmo.core.transfer.Response;
import se.ifmo.core.io.IOWorker;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * UDP Server
 *
 * @see Request - request data transfer objects
 * @see Response - response data transfer objects
 * @see Handler - processing logic
 * @see IOWorker - console input/output
 * @see ServerConfiguration - server configuration
 * @see DatagramSocket - UDP socket
 */

public class Server implements AutoCloseable {
    private final Logger log = LoggerFactory.getLogger(Server.class);

    private static final int BUFFER_SIZE = 10000;

    private final int port;

    private DatagramSocket socket;
    private final IOWorker console;
    private final UserService userService;

    private boolean isRunning = true;

    ExecutorService processPool = Executors.newCachedThreadPool();
    ExecutorService answerPool = Executors.newCachedThreadPool();

    public Server(int port, IOWorker console, UserService userService) {
        this.port = port;
        this.console = console;
        this.userService = userService;
    }

    /**
     * Start server
     *
     * @see #listen() - listen for incoming packets
     * @see #processCommand(String) - process server commands
     */
    public void start() {
        log.info("Server is starting...");
        try {
            socket = new DatagramSocket(port);
            log.info("Server is listening on port {}", port);

            console.write("Enter 'help' to see available commands.");

            listen();
        } catch (Exception e) {
            log.error("Error during server start: {}", e.getMessage());
        }
    }

    /**
     * Listen for incoming packets
     */
    private void listen() {
        byte[] buffer = new byte[BUFFER_SIZE];
        DatagramPacket incomingPacket = new DatagramPacket(buffer, buffer.length);

        while (isRunning) {
            if (console.ready()) console.write(processCommand(console.read()));

            try {
                socket.setSoTimeout(1000);
                socket.receive(incomingPacket);
            } catch (IOException ste) {
                continue;
            }

            // Для чтения использовать создание нового потока & обработка с помощью cached thread pool
            new Thread(() -> processPool.execute(() -> processPacket(incomingPacket))).start();
        }
    }

    /**
     * Process incoming packet
     *
     * @param packet - incoming packet
     */
    private void processPacket(DatagramPacket packet) {
        try {
            Request request = deserializeRequest(packet.getData());
            log.info("Received request: {} from {}", request.toString(), packet);

            Optional<User> optionalUser = userService.findByUsernameAndPassword(request.userLoginDto().username(), request.userLoginDto().password());

            if (optionalUser.isEmpty() && userService.existsByUsername(request.userLoginDto().username())) {
                // отправка с помощью cached thread pool
                answerPool.execute(() -> send(new Response("Incorrect username / password"), packet.getAddress(), packet.getPort()));
            }

            User user = optionalUser.orElse(userService.save(request.userLoginDto().username(), request.userLoginDto().password()));

            Response response = request.commandDto().command().execute(request, user);
            log.info("Sending response: {}", response.toString());

            // отправка с помощью cached thread pool
            answerPool.execute(() -> send(response, packet.getAddress(), packet.getPort()));
        } catch (IOException | ClassNotFoundException e) {
            log.warn(e.getMessage());
        }
    }

    /**
     * Process server commands
     *
     * @param command - command to process
     * @return - response message
     */
    private String processCommand(String command) {
        return switch (command) {
            case "help" -> "[x] available commands: help, stop, save";
            case "save" -> {
//                ResourceController.getInstance().save();
                yield "[x] saved";
            }
            case "stop" -> {
                isRunning = false;
                yield "[x] server stopped";
            }
            default -> String.format("[x] unknown command: %s", command);
        };
    }

    private void send(Response response, InetAddress clientAddress, int clientPort) {
        try {
            byte[] responseData = serializeResponse(response);
            DatagramPacket responsePacket = new DatagramPacket(responseData, responseData.length, clientAddress, clientPort);
            socket.send(responsePacket);
        } catch (IOException e) {
            log.warn(e.getMessage());
        }
    }

    /**
     * Serialize response
     *
     * @param response - response to serialize
     * @return - serialized response
     * @throws IOException - if an I/O error occurs
     */
    private byte[] serializeResponse(Response response) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(response);
        oos.flush();
        return bos.toByteArray();
    }

    /**
     * Deserialize request
     *
     * @param data - data to deserialize
     * @return - deserialized request
     * @throws IOException            - if an I/O error occurs
     * @throws ClassNotFoundException - if class not found
     */
    private Request deserializeRequest(byte[] data) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        ObjectInputStream ois = new ObjectInputStream(bis);
        return (Request) ois.readObject();
    }

    /**
     * Close server
     *
     * @throws Exception - if an error occurs
     */
    @Override
    public void close() throws Exception {
        log.info("Server is stopping");

        if (socket != null && !socket.isClosed()) socket.close();
        socket = null;

        isRunning = false;
    }
}
