package se.ifmo.core.commands.list;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import se.ifmo.core.collection.model.HumanBeing;
import se.ifmo.core.commands.Command;
import se.ifmo.core.collection.model.User;
import se.ifmo.core.collection.service.HumanBeingService;
import se.ifmo.core.transfer.Request;
import se.ifmo.core.transfer.Response;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class AverageOfImpactSpeed extends Command {
    transient HumanBeingService humanBeingService;

    public AverageOfImpactSpeed(HumanBeingService humanBeingService) {
        super("average_of_impact_speed", "вывести среднее значение поля impactSpeed для всех элементов коллекции");
        this.humanBeingService = humanBeingService;
    }

    @Override
    public Response execute(Request args, User user) {
        return new Response("среднее значение поля impactSpeed: " +
                humanBeingService.findAll().stream()
                        .mapToLong(HumanBeing::getImpactSpeed)
                        .average().orElse(0));
    }
}
