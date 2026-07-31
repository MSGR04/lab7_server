package se.ifmo.core.commands;

import se.ifmo.core.collection.model.User;
import se.ifmo.core.transfer.Request;
import se.ifmo.core.transfer.Response;

import java.io.Serializable;

public abstract class Command implements Serializable {
    private final String name;
    private final String help;
    private final int requiredResources;

    public Command(String name, String help) {
        this.name = name;
        this.help = help;
        this.requiredResources = 0;
    }

    public Command(String name, String help, int requiredResources) {
        this.name = name;
        this.help = help;
        this.requiredResources = requiredResources;
    }

    public String getName() {
        return name;
    }

    public String getHelp() {
        return help;
    }

    public int getRequiredResources() {
        return requiredResources;
    }

    public abstract Response execute(Request args, User user);
}
