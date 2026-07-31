package se.ifmo.core.collection.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import se.ifmo.core.collection.exceptions.InvalidArgumentException;
import se.ifmo.core.collection.exceptions.NullableFieldException;

import java.io.Serializable;
import java.util.Objects;

@Entity @Table(name = "coordinates")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@AllArgsConstructor @NoArgsConstructor
public class Coordinates implements Comparable<Coordinates>, Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Integer x;

    public void setX(Integer x) {
        if (x == null) throw new NullableFieldException("x");
        if (x > 844) throw new InvalidArgumentException("x", "Максимальное значение поля: 844");
        this.x = x;
    }

    private float y;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return Float.compare(y, that.y) == 0 && Objects.equals(x, that.x);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public int compareTo(Coordinates o) {
        return x.compareTo(o.x) + Float.compare(y, o.y);
    }
}
