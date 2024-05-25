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

import java.util.stream.Collectors;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class PrintFieldAscendingMinutesOfWaiting extends Command {
    transient HumanBeingService humanBeingService;
    transient  HumanBeingMapper humanBeingMapper;

    public PrintFieldAscendingMinutesOfWaiting(HumanBeingService humanBeingService, HumanBeingMapper humanBeingMapper) {
        super("print_field_ascending_minutes_of_waiting", "вывести значения поля minutesOfWaiting всех элементов в порядке возрастания");
        this.humanBeingService = humanBeingService;
        this.humanBeingMapper = humanBeingMapper;
    }

    @Override
    public Response execute(Request args, User user) {
        return new Response(humanBeingService.findAll().stream()
                .map(HumanBeing::getMinutesOfWaiting)
                .sorted()
                .map(minutesOfWaiting -> "\n" + minutesOfWaiting)
                .collect(Collectors.joining()));
    }
}
