package se.ifmo.core.collection.dto;

import java.io.Serializable;

public record UserLoginDto(String username, String password) implements Serializable {
}
