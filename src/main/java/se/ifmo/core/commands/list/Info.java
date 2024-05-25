package se.ifmo.core.commands.list;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import se.ifmo.core.commands.Command;
import se.ifmo.core.collection.model.User;
import se.ifmo.core.collection.repository.HumanBeingRepository;
import se.ifmo.core.collection.service.HumanBeingService;
import se.ifmo.core.transfer.Request;
import se.ifmo.core.transfer.Response;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class Info extends Command {
    transient HumanBeingService humanBeingService;

    public Info(HumanBeingService humanBeingService, HumanBeingRepository humanBeingRepository) {
        super("info", "вывести в стандартный поток вывода информацию о коллекции");
        this.humanBeingService = humanBeingService;
    }

    @Override
    public Response execute(Request args, User user) {
        return new Response(String.format("количество элементов: %d\nдата инициализации: %s",
                humanBeingService.count(), "using postgres d/b he-he"));
    }
}
