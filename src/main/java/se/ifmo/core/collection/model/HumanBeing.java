package se.ifmo.core.collection.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;
import se.ifmo.core.collection.exceptions.InvalidArgumentException;
import se.ifmo.core.collection.exceptions.NullableFieldException;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "human_being")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@AllArgsConstructor @NoArgsConstructor
public class HumanBeing implements Comparable<HumanBeing>, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    public void setId(Long id) {
        if (id == null) throw new NullableFieldException("id");
        if (id <= 0) throw new InvalidArgumentException("id", "Значение поля должно быть больше 0");
        this.id = id;
    }

    @Column(nullable = false)
    @NotBlank
    String name;

    @ManyToOne
    @JoinColumn(nullable = false)
    User owner;

    public void setName(String name) {
        if (name == null) throw new NullableFieldException("name");
        if (name.isBlank() || name.isEmpty()) throw new InvalidArgumentException("name", "Строка не может быть пустой");
        this.name = name;
    }

    @OneToOne
    @JoinColumn(nullable = false)
    Coordinates coordinates;

    public void setCoordinates(Coordinates coordinates) {
        if (coordinates == null) throw new NullableFieldException("coordinates");
        this.coordinates = coordinates;
    }

    @Column(nullable = false, updatable = false)
    Date creationDate = new Date();

    public void setCreationDate(Date creationDate) {
        if (creationDate == null) throw new NullableFieldException("creationDate");
        this.creationDate = creationDate;
    }

    boolean realHero;

    @Column(nullable = false)
    Boolean hasToothpick;

    public void setHasToothpick(Boolean hasToothpick) {
        if (hasToothpick == null) throw new NullableFieldException("hasToothpick");
        this.hasToothpick = hasToothpick;
    }

    long impactSpeed;

    public void setImpactSpeed(long impactSpeed) {
        if (impactSpeed <= -953)
            throw new InvalidArgumentException("impactSpeed", "Значение поля должно быть больше -953");
        this.impactSpeed = impactSpeed;
    }

    String soundtrackName;

    public void setSoundtrackName(String soundtrackName) {
        if (soundtrackName == null) throw new NullableFieldException("soundtrackName");
        this.soundtrackName = soundtrackName;
    }

    int minutesOfWaiting;

    @Column(nullable = false)
    Mood mood;

    /**
     * Поле не может быть null
     */
    @OneToOne
    @JoinColumn(nullable = false)
    Car car;

    public void setCar(Car car) {
        if (car == null) throw new NullableFieldException("car");
        this.car = car;
    }

    @Override
    public String toString() {
        return "HumanBeing{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", realHero=" + realHero +
                ", hasToothpick=" + hasToothpick +
                ", impactSpeed=" + impactSpeed +
                ", soundtrackName='" + soundtrackName + '\'' +
                ", minutesOfWaiting=" + minutesOfWaiting +
                ", mood=" + mood +
                ", car=" + car +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HumanBeing that = (HumanBeing) o;
        return realHero == that.realHero && impactSpeed == that.impactSpeed && minutesOfWaiting == that.minutesOfWaiting && Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(coordinates, that.coordinates) && Objects.equals(creationDate, that.creationDate) && Objects.equals(hasToothpick, that.hasToothpick) && Objects.equals(soundtrackName, that.soundtrackName) && mood == that.mood && Objects.equals(car, that.car);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, realHero, hasToothpick, impactSpeed, soundtrackName, minutesOfWaiting, mood, car);
    }

    @Override
    public int compareTo(HumanBeing o) {
        return id.compareTo(o.id) + name.compareTo(o.name) + coordinates.compareTo(o.coordinates) + creationDate.compareTo(o.creationDate) + Boolean.compare(realHero, o.realHero) + hasToothpick.compareTo(o.hasToothpick) + Long.compare(impactSpeed, o.impactSpeed) + soundtrackName.compareTo(o.soundtrackName) + Integer.compare(minutesOfWaiting, o.minutesOfWaiting) + mood.compareTo(o.mood) + car.compareTo(o.car);
    }
}
