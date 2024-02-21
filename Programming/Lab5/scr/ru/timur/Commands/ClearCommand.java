package ru.timur.Commands;

import ru.timur.Controllers.CollectionController;
import ru.timur.Exceptions.InvalidDataException;
import ru.timur.Exceptions.WrongAmountOfArgumentsException;
import ru.timur.UI.Console;

import java.io.FileNotFoundException;
/**
 * Class with realization of clear command
 * <p>This command is used to clear collection
 * @see UserCommand
 * @see ICommand
 */
public class ClearCommand extends UserCommand {
    /**
     * Controller of collection which is used to clear it
     */
    private CollectionController collectionController;

    /**
     * ClearCommand constructor
     * <p> Firstly it initializes super constructor by command name, arguments and description
     * @param collectionController
     */
    public ClearCommand(CollectionController collectionController) {
        super("clear", "delete all element from collection");
        this.collectionController = collectionController;
    }

    /**
     * Method to complete clear command
     * <p>It clears collection
     */
    @Override
    public void execute() {
        this.collectionController.clear();
        Console.getInstance().printLn("Collection cleared successfully!");
    }

    /**
     * Method checks if amount arguments is correct
     * @param commandArgs String array with different arguments
     * @throws WrongAmountOfArgumentsException If it is more than zero arguments
     */
    @Override
    public void initCommandArgs(String[] commandArgs) throws WrongAmountOfArgumentsException {
        if(commandArgs.length != 0){
            throw new WrongAmountOfArgumentsException("Wrong amount of arguments!", 0, commandArgs.length);
        }
    }
}
