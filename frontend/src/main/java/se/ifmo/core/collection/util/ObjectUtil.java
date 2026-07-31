package se.ifmo.core.collection.util;

import se.ifmo.core.collection.exceptions.NullableFieldException;
import se.ifmo.core.collection.model.Car;
import se.ifmo.core.collection.model.Coordinates;
import se.ifmo.core.collection.model.HumanBeing;
import se.ifmo.core.collection.model.Mood;
import se.ifmo.core.io.IOWorker;

import java.util.Arrays;
import java.util.Deque;
import java.util.function.Consumer;
import java.util.function.Function;

public class ObjectUtil {
    public static HumanBeing collect(Deque<String> requests) {
        try {
            HumanBeing humanBeing = new HumanBeing();

            while (!input(humanBeing::setName, Function.identity(), requests.pollLast())) ;

            Coordinates coordinates = new Coordinates();

            while (!input(coordinates::setX, Integer::parseInt, requests.pollLast())) ;
            while (!input(coordinates::setY, Float::parseFloat, requests.pollLast())) ;

            humanBeing.setCoordinates(coordinates);

            while (!input(humanBeing::setRealHero, Boolean::parseBoolean, requests.pollLast())) ;
            while (!input(humanBeing::setHasToothpick, Boolean::parseBoolean, requests.pollLast())) ;
            while (!input(humanBeing::setImpactSpeed, Long::parseLong, requests.pollLast())) ;
            while (!input(humanBeing::setSoundtrackName, Function.identity(), requests.pollLast())) ;
            while (!input(humanBeing::setMinutesOfWaiting, Integer::parseInt, requests.pollLast())) ;
            while (!input(humanBeing::setMood, Mood::valueOf, requests.pollLast())) ;

            Car car = new Car();

            while (!input(car::setName, Function.identity(), requests.pollLast())) ;
            while (!input(car::setCool, Boolean::parseBoolean, requests.pollLast())) ;

            humanBeing.setCar(car);

            return humanBeing;
        } catch (Exception ex) {
            return null;
        }
    }

    public static HumanBeing collect(IOWorker ioWorker) {
        try {
            HumanBeing humanBeing = new HumanBeing();

            while (!input("name", humanBeing::setName, s -> s, ioWorker)) ;

            Coordinates coordinates = new Coordinates();

            while (!input("x", coordinates::setX, Integer::parseInt, ioWorker)) ;
            while (!input("y", coordinates::setY, Float::parseFloat, ioWorker)) ;

            humanBeing.setCoordinates(coordinates);

            while (!input("realHero", humanBeing::setRealHero, Boolean::parseBoolean, ioWorker)) ;
            while (!input("hasToothpick", humanBeing::setHasToothpick, Boolean::parseBoolean, ioWorker)) ;
            while (!input("impactSpeed", humanBeing::setImpactSpeed, Long::parseLong, ioWorker)) ;
            while (!input("soundtrackName", humanBeing::setSoundtrackName, Function.identity(), ioWorker)) ;
            while (!input("minutesOfWaiting", humanBeing::setMinutesOfWaiting, Integer::parseInt, ioWorker)) ;
            while (!input("mood " + Arrays.toString(Mood.values()), humanBeing::setMood, Mood::valueOf, ioWorker)) ;

            Car car = new Car();

            while (!input("carName", car::setName, Function.identity(), ioWorker)) ;
            while (!input("carCool", car::setCool, Boolean::parseBoolean, ioWorker)) ;

            humanBeing.setCar(car);

            return humanBeing;
        } catch (Throwable ex) {
            ioWorker.write(ex.getMessage());
            return null;
        }
    }

    private static <K> boolean input(String fieldName, Consumer<K> setter, Function<String, K> parser, IOWorker ioWorker) throws Throwable {
        try {
            String line = ioWorker.writeAndRead(" - %s: ", fieldName);
            if (line == null || line.equals("return")) throw new Throwable("вызван return");

            setter.accept(parser.apply(line));
            return true;
        } catch (Exception ex) {
            ioWorker.write(ex.getMessage());
            return false;
        }
    }

    private static <K> boolean input(Consumer<K> setter, Function<String, K> parser, String val) throws Exception {
        try {
            if (val == null || val.equals("return")) throw new Exception("вызван return");

            setter.accept(parser.apply(val));
            return true;
        } catch (NullableFieldException | IllegalArgumentException ex) {
            return false;
        }
    }


}
