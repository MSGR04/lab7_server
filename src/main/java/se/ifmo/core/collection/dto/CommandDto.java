package se.ifmo.core.collection.dto;

import se.ifmo.core.commands.Command;

import java.io.Serializable;
import java.util.List;

public record CommandDto(Command command, String text, List<HumanBeingDto> humanBeingDtos) implements Serializable {}
