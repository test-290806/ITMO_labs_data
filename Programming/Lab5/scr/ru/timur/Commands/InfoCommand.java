package ru.timur.Commands;

import ru.timur.Controllers.CollectionController;
import ru.timur.Exceptions.InvalidDataException;
import ru.timur.Exceptions.WrongAmountOfArgumentsException;
import ru.timur.UI.Console;

/**
 * Class with realization of info command
 * <p>This command is used to print information about collection
 * @see UserCommand
 * @see ICommand
 */
public class InfoCommand extends UserCommand {
    /**
     * Controller of collection which is used get information about collection
     */
    private CollectionController collectionController;

    /**
     * InfoCommand constructor
     * <p> Firstly it initializes super constructor by command name, arguments and description
     * @param collectionController
     */
    public InfoCommand(CollectionController collectionController) {
        super("info", "print information about collection");
        this.collectionController = collectionController;
    }

    /**
     * Method to complete info command
     * <p>It prints info from collection controller
     */
    @Override
    public void execute() {
        Console.getInstance().printLn(this.collectionController.getInfo());
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
