package ru.timur.Commands;

import ru.timur.Exceptions.InvalidDataException;
import ru.timur.Exceptions.WrongAmountOfArgumentsException;

import java.util.Objects;

/**
 * Abstract class for all commands from user
 */
public abstract class UserCommand implements ICommand {
    /**
     * Command name
     */
    private final String name;
    /**
     * Command arguments as one String
     */
    private final String arguments;
    /**
     * Command description
     */
    private final String description;

    /**
     * UserCommand constructor for commands without arguments
     * @param name
     * @param description
     */
    public UserCommand(String name, String description){
        this.name = name;
        this.arguments = "";
        this.description = description;
    }

    /**
     * UserCommand constructor for commands with arguments
     * @param name
     * @param arguments
     * @param description
     */
    public UserCommand(String name, String arguments, String description){
        this.name = name;
        this.arguments = arguments;
        this.description = description;
    }

    /**
     * Method to get command name
     * @return name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Method to check and load arguments of command
     * @param commandArgs String array with different arguments
     * @throws WrongAmountOfArgumentsException If number of arguments is wrong for given command
     * @throws InvalidDataException if command arguments are not valid
     */
    public abstract void initCommandArgs(String[] commandArgs) throws InvalidDataException, WrongAmountOfArgumentsException;

    /**
     * Method to get String representation of command
     * @return String with command name, arguments and description
     */
    @Override
    public String toString() {
        String res = this.name;
        if(!this.arguments.isEmpty()) res += " " + this.arguments;
        res += ": " + this.description;
        return res;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserCommand command)) return false;
        return Objects.equals(name, command.name) && Objects.equals(arguments, command.arguments) && Objects.equals(description, command.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, arguments, description);
    }
}
