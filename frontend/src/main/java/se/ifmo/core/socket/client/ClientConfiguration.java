package se.ifmo.core.socket.client;

import java.net.InetAddress;

public class ClientConfiguration {
    private final InetAddress host;
    private final int port;

    public ClientConfiguration(InetAddress host, int port) {
        this.host = host;
        this.port = port;
    }

    public InetAddress getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }
}
