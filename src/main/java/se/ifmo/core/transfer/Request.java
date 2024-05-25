package se.ifmo.core.transfer;

import se.ifmo.core.collection.dto.CommandDto;
import se.ifmo.core.collection.dto.UserLoginDto;

import java.io.Serial;
import java.io.Serializable;

public record Request(CommandDto commandDto, UserLoginDto userLoginDto) implements Serializable {
    @Serial
    private static final long serialVersionUID = 228L;
}
