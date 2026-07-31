package se.ifmo.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import se.ifmo.core.collection.mapper.HumanBeingMapper;
import se.ifmo.core.commands.Command;
import se.ifmo.core.io.console.BufferedConsoleWorker;
import se.ifmo.core.socket.client.Client;
import se.ifmo.core.socket.client.ClientConfiguration;

import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.List;

@Configuration
public class SocketConfiguration {
    @Bean(destroyMethod = "close")
    public Client client(@Qualifier("commands") List<Command> commands, HumanBeingMapper humanBeingMapper) throws UnknownHostException, SocketException {
        return new Client(new ClientConfiguration(InetAddress.getLocalHost(), 1313), new BufferedConsoleWorker(), commands, humanBeingMapper);
    }
}
