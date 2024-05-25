package se.ifmo.core.collection.mapper;

import org.springframework.stereotype.Component;
import se.ifmo.core.collection.dto.CarDto;
import se.ifmo.core.collection.model.Car;

@Component
public class CarMapper implements Mapper<Car, CarDto> {
    @Override
    public Car fromDto(CarDto dto) {
        return new Car(
                dto.getId(),
                dto.getName(),
                dto.getCool()
        );
    }

    @Override
    public CarDto toDto(Car obj) {
        return new CarDto(
                obj.getId(),
                obj.getName(),
                obj.getCool()
        );
    }
}
