package se.ifmo.core.collection.dto;

import java.io.Serial;
import java.io.Serializable;

public class CoordinatesDto implements Serializable {
    @Serial private final long seralVersionUID = 228L;

    Long id;
    Integer x;
    private float y;

    public CoordinatesDto(Long id, Integer x, float y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Integer getX() {
        return x;
    }
    public void setX(Integer x) {
        this.x = x;
    }
    public float getY() {
        return y;
    }
    public void setY(float y) {
        this.y = y;
    }
}
