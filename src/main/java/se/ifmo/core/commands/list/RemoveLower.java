package se.ifmo.core.commands.list;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import se.ifmo.core.collection.mapper.HumanBeingMapper;
import se.ifmo.core.collection.model.HumanBeing;
import se.ifmo.core.commands.Command;
import se.ifmo.core.collection.model.User;
import se.ifmo.core.collection.service.HumanBeingService;
import se.ifmo.core.transfer.Request;
import se.ifmo.core.transfer.Response;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RemoveLower extends Command {
    transient HumanBeingService humanBeingService;
    transient  HumanBeingMapper humanBeingMapper;

    public RemoveLower(HumanBeingService humanBeingService, HumanBeingMapper humanBeingMapper) {
        super("remove_lower", "{element} - удалить из коллекции все элементы, меньшие, чем заданный");
        this.humanBeingService = humanBeingService;
        this.humanBeingMapper = humanBeingMapper;
    }

    @Override
    public Response execute(Request args, User user) {
        if (args.commandDto().humanBeingDtos().isEmpty())
            return new Response("коллекция пуста");

        HumanBeing selected = humanBeingMapper.fromDto(args.commandDto().humanBeingDtos().get(0));

        humanBeingService.findAllByOwner(user).stream()
                .filter(temp -> temp.compareTo(selected) < 0)
                .forEach(temp -> humanBeingService.removeByIdAndOwner(temp.getId(), temp.getOwner()));

        return new Response("все элементы, меньшие, чем заданный, успешно удалены");
    }
}
