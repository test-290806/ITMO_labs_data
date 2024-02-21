package ru.timur.Exceptions;

/**
 * Signals that command got wrong amount of arguments
 */
public class WrongAmountOfArgumentsException extends IllegalArgumentException{
    /**
     * Expected number of arguments
     */
    private final int expectedArguments;
    /**
     * Given number of arguments
     */
    private final int givenArguments;
    /**
     * message for user
     */
    String message;

    /**
     * Constructs an {@code WrongAmountOfArgumentsException} with the specified detail message and additional data
     *
     * @param message
     *        The detail message
     * @param expectedArguments Expected number of arguments
     * @param givenArguments Given number of arguments
     */
    public WrongAmountOfArgumentsException(String message, int expectedArguments, int givenArguments){
        super(message);
        this.expectedArguments = expectedArguments;
        this.givenArguments = givenArguments;
        this.message = message;
    }

    /**
     * Returns message with information about expected and given number of arguments
     * @return message
     */
    @Override
    public String getMessage() {
        return String.format("%s Expected %d, got %d", message, expectedArguments, givenArguments);
    }
}
