package ru.timur.Commands;

/**
 * Interface of all commands
 */
public interface ICommand {
    /**
     * Method to get command name
     * @return String command name
     */
    String getName();

    /**
     * Method to execute command
     * @throws Exception If any error occurred
     */
    void execute() throws Exception;
}
