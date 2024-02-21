package ru.timur.Exceptions;

import java.io.FileNotFoundException;

/**
 * Signals that file has wrong permissions
 */
public class WrongFilePermissionsException extends FileNotFoundException {
    /**
     * Constructs an {@code WrongFilePermissionsException} with the specified detail message.
     *
     * @param message
     *        The detail message
     */
    public WrongFilePermissionsException(String message){
        super(message);
    }
}
