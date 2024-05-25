package se.ifmo.core.collection.mapper;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import se.ifmo.core.collection.dto.HumanBeingDto;
import se.ifmo.core.collection.model.HumanBeing;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class HumanBeingMapper implements Mapper<HumanBeing, HumanBeingDto> {
    UserMapper userMapper;
    CoordinatesMapper coordinatesMapper;
    CarMapper carMapper;

    public HumanBeing fromDto(HumanBeingDto dto) {
        return new HumanBeing(
                dto.getId(),
                dto.getName(),
                userMapper.fromDto(dto.getOwner()),
                coordinatesMapper.fromDto(dto.getCoordinates()),
                dto.getCreationDate(),
                dto.isRealHero(),
                dto.getHasToothpick(),
                dto.getImpactSpeed(),
                dto.getSoundtrackName(),
                dto.getMinutesOfWaiting(),
                dto.getMood(),
                carMapper.fromDto(dto.getCar())
        );
    }

    public HumanBeingDto toDto(HumanBeing humanBeing) {
        return new HumanBeingDto(
                humanBeing.getId(),
                humanBeing.getName(),
                userMapper.toDto(humanBeing.getOwner()),
                coordinatesMapper.toDto(humanBeing.getCoordinates()),
                humanBeing.getCreationDate(),
                humanBeing.isRealHero(),
                humanBeing.getHasToothpick(),
                humanBeing.getImpactSpeed(),
                humanBeing.getSoundtrackName(),
                humanBeing.getMinutesOfWaiting(),
                humanBeing.getMood(),
                carMapper.toDto(humanBeing.getCar()));
    }
}
