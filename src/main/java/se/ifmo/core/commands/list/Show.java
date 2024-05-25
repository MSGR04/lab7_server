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
public class Show extends Command {
    transient HumanBeingService humanBeingService;
    transient  HumanBeingMapper humanBeingMapper;

    public Show(HumanBeingService humanBeingService, HumanBeingMapper humanBeingMapper) {
        super("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        this.humanBeingService = humanBeingService;
        this.humanBeingMapper = humanBeingMapper;
    }

    @Override
    public Response execute(Request args, User user) {
        return new Response("все элементы коллекции", humanBeingService.findAll().stream().map(humanBeingMapper::toDto).toList());
    }
}
