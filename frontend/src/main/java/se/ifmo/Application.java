package se.ifmo;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import se.ifmo.core.io.IOWorker;
import se.ifmo.core.io.console.BufferedConsoleWorker;
import se.ifmo.core.socket.client.Client;
import se.ifmo.core.socket.client.ClientConfiguration;

import java.net.InetAddress;

@SpringBootApplication
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class Application implements ApplicationRunner {
    Client client;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        try {
            client.start();
        } catch (Exception e) {
            System.err.println("Ошибка во время исполнения: " + e.getMessage());
        }
    }
}


