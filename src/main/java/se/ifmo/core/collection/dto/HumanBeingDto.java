package se.ifmo.core.collection.dto;

import se.ifmo.core.collection.model.Mood;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

public class HumanBeingDto implements Serializable {
    @Serial private final long seralVersionUID = 228L;

    Long id;
    String name;
    UserDto owner;
    CoordinatesDto coordinates;
    Date creationDate = new Date();
    boolean realHero;
    Boolean hasToothpick;
    long impactSpeed;
    String soundtrackName;
    int minutesOfWaiting;
    Mood mood;
    CarDto car;

    public HumanBeingDto(Long id, String name, UserDto owner, CoordinatesDto coordinates, Date creationDate, boolean realHero, Boolean hasToothpick, long impactSpeed, String soundtrackName, int minutesOfWaiting, Mood mood, CarDto car) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.realHero = realHero;
        this.hasToothpick = hasToothpick;
        this.impactSpeed = impactSpeed;
        this.soundtrackName = soundtrackName;
        this.minutesOfWaiting = minutesOfWaiting;
        this.mood = mood;
        this.car = car;
    }

    public long getSeralVersionUID() {
        return seralVersionUID;
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
    public UserDto getOwner() {
        return owner;
    }
    public void setOwner(UserDto owner) {
        this.owner = owner;
    }
    public CoordinatesDto getCoordinates() {
        return coordinates;
    }
    public void setCoordinates(CoordinatesDto coordinates) {
        this.coordinates = coordinates;
    }
    public Date getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
    public boolean isRealHero() {
        return realHero;
    }
    public void setRealHero(boolean realHero) {
        this.realHero = realHero;
    }
    public Boolean getHasToothpick() {
        return hasToothpick;
    }
    public void setHasToothpick(Boolean hasToothpick) {
        this.hasToothpick = hasToothpick;
    }
    public long getImpactSpeed() {
        return impactSpeed;
    }
    public void setImpactSpeed(long impactSpeed) {
        this.impactSpeed = impactSpeed;
    }
    public String getSoundtrackName() {
        return soundtrackName;
    }
    public void setSoundtrackName(String soundtrackName) {
        this.soundtrackName = soundtrackName;
    }
    public int getMinutesOfWaiting() {
        return minutesOfWaiting;
    }
    public void setMinutesOfWaiting(int minutesOfWaiting) {
        this.minutesOfWaiting = minutesOfWaiting;
    }
    public Mood getMood() {
        return mood;
    }
    public void setMood(Mood mood) {
        this.mood = mood;
    }
    public CarDto getCar() {

        return car;
    }
    public void setCar(CarDto car) {
        this.car = car;
    }
}
