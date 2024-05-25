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
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class Add extends Command {
    transient HumanBeingService humanBeingService;
    transient HumanBeingMapper humanBeingMapper;

    public Add(HumanBeingService humanBeingService, HumanBeingMapper humanBeingMapper) {
        super("add", " - добавить новый элемент с заданным ключом", 1);
        this.humanBeingService = humanBeingService;
        this.humanBeingMapper = humanBeingMapper;
    }

    @Override
    public Response execute(Request args, User user) {
        if (args.commandDto().humanBeingDtos().isEmpty()) return new Response("Вы должны ввести хотя бы один элемент для добавления!");

        HumanBeing humanBeing = humanBeingMapper.fromDto(args.commandDto().humanBeingDtos().get(0));
        humanBeing.setOwner(user);

        return new Response(humanBeingService.save(humanBeing) == null ?
                "элемент успешно добавлен" :
                "произошла ошибка при добавлении элемента");
    }
}
