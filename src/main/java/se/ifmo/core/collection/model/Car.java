package se.ifmo.core.collection.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import se.ifmo.core.collection.exceptions.NullableFieldException;

import java.io.Serializable;
import java.util.Objects;

@Entity @Table(name = "car")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Car implements Comparable<Car>, Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    /**
     * Поле не может быть null
     */
    private String name;

    public void setName(String name) {
        if (name == null) throw new NullableFieldException("name");
        this.name = name;
    }

    /**
     * Поле может быть null
     */
    private Boolean cool;

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", cool=" + cool +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(name, car.name) && Objects.equals(cool, car.cool);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cool);
    }

    @Override
    public int compareTo(Car o) {
        return name.compareTo(o.name) + cool.compareTo(o.cool);
    }
}
