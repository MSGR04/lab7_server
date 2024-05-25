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

import java.util.Collections;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class MaxByRealHero extends Command {
    transient HumanBeingService humanBeingService;
    transient  HumanBeingMapper humanBeingMapper;

    public MaxByRealHero(HumanBeingService humanBeingService, HumanBeingMapper humanBeingMapper) {
        super("max_by_real_hero", "вывести любой объект из коллекции, значение поля realHero которого является максимальным");
        this.humanBeingService = humanBeingService;
        this.humanBeingMapper = humanBeingMapper;
    }

    @Override
    public Response execute(Request args, User user) {
        return humanBeingService.findAll().stream().max((a, b) -> Boolean.compare(a.isRealHero(), b.isRealHero()))
                .map(humanBeing -> new Response("Результат", Collections.singletonList(humanBeingMapper.toDto(humanBeing))))
                .orElse(new Response("коллекция пуста"));
    }
}
