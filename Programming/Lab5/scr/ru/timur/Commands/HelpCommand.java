package ru.timur.Commands;

import ru.timur.Controllers.CommandsController;
import ru.timur.Exceptions.InvalidDataException;
import ru.timur.Exceptions.WrongAmountOfArgumentsException;
import ru.timur.UI.Console;

/**
 * Class with realization of help command
 * <p>This command is used to print description of available commands
 * @see UserCommand
 * @see ICommand
 */
public class HelpCommand extends UserCommand {
    /**
     * Controller of command which is used to get list of all commands
     */
    private CommandsController commandsController;

    /**
     * HelpCommand constructor
     * <p> Firstly it initializes super constructor by command name, arguments and description
     * @param commandsController
     */
    public HelpCommand(CommandsController commandsController) {
        super("help", "print description of available commands");
        this.commandsController = commandsController;
    }

    /**
     * Method to complete help command
     * <p>It gets all commands from commandController and then prints their description
     */
    @Override
    public void execute() {
        this.commandsController.getCommandsList()
                .forEach(command -> Console.getInstance().printLn(command));
    }

    /**
     * Method checks if amount arguments is correct
     * @param commandArgs String array with different arguments
     * @throws WrongAmountOfArgumentsException If number of arguments is not equal to zero
     */
    @Override
    public void initCommandArgs(String[] commandArgs) throws WrongAmountOfArgumentsException {
        if(commandArgs.length != 0) throw new WrongAmountOfArgumentsException("Wrong amount of arguments!", 0, commandArgs.length);
    }
}
