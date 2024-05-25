package se.ifmo.core.commands.list;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import se.ifmo.core.commands.Command;
import se.ifmo.core.collection.model.User;
import se.ifmo.core.collection.service.HumanBeingService;
import se.ifmo.core.transfer.Request;
import se.ifmo.core.transfer.Response;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class Clear extends Command {
    transient HumanBeingService humanBeingService;

    public Clear(HumanBeingService humanBeingService) {
        super("clear", "очистить коллекцию");
        this.humanBeingService = humanBeingService;
    }

    @Override
    public Response execute(Request args, User user) {
        humanBeingService.removeAllByOwner(user);
        return new Response("коллекция успешно очищена!");
    }
}
