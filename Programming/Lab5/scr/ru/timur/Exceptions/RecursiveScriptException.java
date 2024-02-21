package ru.timur.Exceptions;

/**
 * Signals that script is recursive
 */
public class RecursiveScriptException extends Exception{
    /**
     * Constructs an {@code RecursiveScriptException} with the specified detail message.
     *
     * @param message
     *        The detail message
     */
    public RecursiveScriptException(String message){
        super(message);
    }
}
