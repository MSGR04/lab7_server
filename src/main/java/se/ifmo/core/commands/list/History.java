package se.ifmo.core.commands.list;

import se.ifmo.core.commands.Command;
import se.ifmo.core.collection.model.User;
import se.ifmo.core.transfer.Request;
import se.ifmo.core.transfer.Response;

public class History extends Command {
    public History() {
        super("history", "вывести последние 6 команд (без их аргументов)");
    }

    @Override
    public Response execute(Request args, User user) {
//        return new Response(Handler.HISTORY.get(user).stream()
//                .limit(6)
//                .reduce((a, b) -> a + "\n" + b)
//                .orElse("история пуста"));
        return null;
    }
}
