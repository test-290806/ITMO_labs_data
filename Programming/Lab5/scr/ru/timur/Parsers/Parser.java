package ru.timur.Parsers;

import ru.timur.Exceptions.InvalidDataException;

/**
 * Functional Interface to parse different values from String with detailed exceptions
 * @param <T> Type of value to parse
 */
@FunctionalInterface
public interface Parser<T> {
    /**
     * Method to parse value from String
     * @param s String to parse from
     * @return Parsed object
     * @throws InvalidDataException If input String is not valid
     */
    T parse(String s) throws InvalidDataException;
}
