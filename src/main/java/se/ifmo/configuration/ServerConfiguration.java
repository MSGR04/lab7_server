package se.ifmo.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import se.ifmo.core.collection.service.UserService;
import se.ifmo.core.io.console.BufferedConsoleWorker;
import se.ifmo.core.socket.server.Server;

@Configuration
@PropertySource("classpath:server.properties")
public class ServerConfiguration {
    @Bean(destroyMethod = "close")
    @Scope("singleton")
    public Server server(@Value("${server.port}") int port, UserService userService) {
        return new Server(port, new BufferedConsoleWorker(), userService);
    }
}
