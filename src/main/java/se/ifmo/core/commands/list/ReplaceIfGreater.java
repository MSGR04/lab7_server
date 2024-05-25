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

import java.util.Optional;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class ReplaceIfGreater extends Command {
    transient  HumanBeingService humanBeingService;
    transient  HumanBeingMapper humanBeingMapper;

    public ReplaceIfGreater(HumanBeingService humanBeingService, HumanBeingMapper humanBeingMapper) {
        super("replace_if_greater", "{id} {element} - заменить значение по ключу, если новое значение больше старого");
        this.humanBeingService = humanBeingService;
        this.humanBeingMapper = humanBeingMapper;
    }

    @Override
    public Response execute(Request args, User user) {
        if (args.commandDto().humanBeingDtos().isEmpty())
            return new Response("коллекция пуста");

        if (args.commandDto().text() == null || args.commandDto().text().isBlank() || args.commandDto().text().isEmpty())
            return new Response("введите id элемента для замены");
        if (!args.commandDto().text().matches("\\d+"))
            return new Response("id должен быть числом");

        long id = Long.parseLong(args.commandDto().text());
        HumanBeing selected = humanBeingMapper.fromDto(args.commandDto().humanBeingDtos().get(0));

        Optional<HumanBeing> optionalHumanBeingDB = humanBeingService.findByIdAndOwner(id, user);

        if (optionalHumanBeingDB.isEmpty())
            return new Response("элемент с таким id отсутствует / элемент не в вашем владении");

        if (optionalHumanBeingDB.get().compareTo(selected) < 0) {
            selected.setId(optionalHumanBeingDB.get().getId());
            humanBeingService.save(selected);
            return new Response("элемент успешно заменен");
        } else return new Response("новое значение не больше старого");
    }
}
