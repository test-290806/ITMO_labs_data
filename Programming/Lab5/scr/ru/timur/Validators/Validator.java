package ru.timur.Validators;

import ru.timur.Exceptions.InvalidDataException;

/**
 * Functional interface to validate different values with detailed exceptions
 * @param <T> Type of value to be validated
 */
@FunctionalInterface
public interface Validator<T> {
    /**
     * Method to validate value
     * @param value Value to bee validated
     * @throws InvalidDataException If value is not valid
     */
    void validate(T value) throws InvalidDataException;
}