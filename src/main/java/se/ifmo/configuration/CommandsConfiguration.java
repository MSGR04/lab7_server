package se.ifmo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import se.ifmo.core.commands.Command;

import java.util.List;

@Configuration
@ComponentScan("se.ifmo.core.commands")
public class CommandsConfiguration {
    @Bean("commands")
    public List<Command> commands(List<Command> commands) {
        return commands;
    }
}
