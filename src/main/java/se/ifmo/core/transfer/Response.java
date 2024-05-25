package se.ifmo.core.transfer;

import se.ifmo.core.collection.dto.HumanBeingDto;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

public class Response implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final String text;
    private final List<HumanBeingDto> humanBeingDtos;

    public Response(String text, List<HumanBeingDto> humanBeingDtos) {
        this.text = text;
        this.humanBeingDtos = humanBeingDtos;
    }

    public Response(String text) {
        this.text = text;
        this.humanBeingDtos = Collections.emptyList();
    }

    public String getText() {
        return text;
    }

    public List<HumanBeingDto> getHumanBeingDtos() {
        return humanBeingDtos;
    }
}
