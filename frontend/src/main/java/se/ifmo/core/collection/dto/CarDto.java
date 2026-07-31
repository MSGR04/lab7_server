package se.ifmo.core.collection.dto;

import java.io.Serial;
import java.io.Serializable;

public class CarDto implements Serializable {
    @Serial private final long seralVersionUID = 228L;

    Long id;
    private String name;
    private Boolean cool;

    public CarDto(Long id, String name, Boolean cool) {
        this.id = id;
        this.name = name;
        this.cool = cool;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Boolean getCool() {
        return cool;
    }
    public void setCool(Boolean cool) {
        this.cool = cool;
    }
}
