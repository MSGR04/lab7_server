package se.ifmo.core.collection.mapper;

import org.springframework.stereotype.Component;
import se.ifmo.core.collection.dto.CoordinatesDto;
import se.ifmo.core.collection.model.Coordinates;

@Component
public class CoordinatesMapper implements Mapper<Coordinates, CoordinatesDto> {
    @Override
    public Coordinates fromDto(CoordinatesDto dto) {
        return new Coordinates(
                dto.getId(),
                dto.getX(),
                dto.getY()
        );
    }

    @Override
    public CoordinatesDto toDto(Coordinates obj) {
        return new CoordinatesDto(
                obj.getId(),
                obj.getX(),
                obj.getY()
        );
    }
}
