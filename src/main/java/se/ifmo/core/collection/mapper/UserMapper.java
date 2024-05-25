package se.ifmo.core.collection.mapper;

import org.springframework.stereotype.Component;
import se.ifmo.core.collection.dto.UserDto;
import se.ifmo.core.collection.model.User;

@Component
public class UserMapper implements Mapper<User, UserDto> {
    @Override
    public User fromDto(UserDto dto) {
        return new User(
                dto.getId(),
                dto.getUsername(),
                dto.getPassword()
        );
    }

    @Override
    public UserDto toDto(User obj) {
        return new UserDto(
                obj.getId(),
                obj.getUsername(),
                obj.getPassword()
        );
    }
}
