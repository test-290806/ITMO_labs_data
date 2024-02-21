package ru.timur.Parsers;

import ru.timur.Collection.Color;
import ru.timur.Collection.Country;
import ru.timur.Collection.Status;
import ru.timur.Constants;
import ru.timur.Exceptions.InvalidDataException;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;

/**
 * Class with parsers which are required to read Worker objects
 */
public class WorkerParsers {
    /**
     * String parser
     */
    public static Parser<String> stringParser = s -> s;
    /**
     * Long parser
     */
    public static Parser<Long> longParser = s -> {
        try{
            return Long.parseLong(s);
        } catch (NumberFormatException e){
            throw new InvalidDataException("Value must be a long!");
        }
    };
    /**
     * Integer parser
     */
    public static Parser<Integer> integerParser = s -> {
        try{
            return Integer.parseInt(s);
        } catch (NumberFormatException e){
            throw new InvalidDataException("Value must be an integer!");
        }
    };
    /**
     * Double parser
     */
    public static Parser<Double> doubleParser = s -> {
        try{
            return Double.parseDouble(s);
        } catch (NumberFormatException e){
            throw new InvalidDataException("Value must be a double!");
        }
    };
    /**
     * LocalDateTime parser
     */
    public static Parser<LocalDateTime> localDateTimeParser = s -> {
        try{
            return LocalDateTime.parse(s, Constants.formatter);
        } catch (DateTimeParseException e){
            throw new InvalidDataException("Wrong date format!");
        }
    };
    /**
     * Status parser
     */
    public static Parser<Status> statusParser = s -> {
        try{
            return Status.valueOf(s);
        } catch (IllegalArgumentException e){
            throw new InvalidDataException("Status not found! Please choose value from list!");
        }
    };
    /**
     * Color parser
     */
    public static Parser<Color> eyeColorParser = s -> {
        try{
            return Color.valueOf(s);
        } catch (IllegalArgumentException e){
            throw new InvalidDataException("Color not found! Please choose value from list!");
        }
    };
    /**
     * Country parser
     */
    public static Parser<Country> nationalityParser = s -> {
        try{
            return Country.valueOf(s);
        } catch (IllegalArgumentException e){
            throw new InvalidDataException("Country not found! Please choose value from list!");
        }
    };
}
