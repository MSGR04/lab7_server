package se.ifmo.core.commands.list;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import se.ifmo.core.commands.Command;
import se.ifmo.core.collection.mapper.HumanBeingMapper;
import se.ifmo.core.collection.model.User;
import se.ifmo.core.collection.service.HumanBeingService;
import se.ifmo.core.transfer.Request;
import se.ifmo.core.transfer.Response;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class RemoveKey extends Command {
    transient HumanBeingService humanBeingService;
    transient  HumanBeingMapper humanBeingMapper;

    public RemoveKey(HumanBeingService humanBeingService, HumanBeingMapper humanBeingMapper) {
        super("remove_key", "{id} - удалить элемент из коллекции по его ключу");
        this.humanBeingService = humanBeingService;
        this.humanBeingMapper = humanBeingMapper;
    }

    @Override
    public Response execute(Request args, User user) {
        if (args.commandDto().text() == null || args.commandDto().text().isEmpty() || args.commandDto().text().isBlank())
            return new Response("введите id элемента для удаления");
        if (!args.commandDto().text().matches("\\d+"))
            return new Response("id должен быть числом");

        long id = Long.parseLong(args.commandDto().text());

        return new Response(humanBeingService.removeByIdAndOwner(id, user) ?
                "элемент успешно удален" :
                "элемент с таким id отсутствует");
    }
}
