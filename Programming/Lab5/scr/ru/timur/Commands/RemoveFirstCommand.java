package ru.timur.Commands;

import ru.timur.Controllers.CollectionController;
import ru.timur.Exceptions.InvalidDataException;
import ru.timur.Exceptions.WrongAmountOfArgumentsException;
import ru.timur.UI.Console;

import java.util.NoSuchElementException;

/**
 * Class with realization of remove_first command
 * <p>This command is used to remove first element from collection
 * @see UserCommand
 * @see ICommand
 */
public class RemoveFirstCommand extends UserCommand {
    /**
     * Controller of collection which is used to remove element
     */
    private CollectionController collectionController;

    /**
     * RemoveFirstCommand constructor
     * <p> Firstly it initializes super constructor by command name, arguments and description
     * @param collectionController
     */
    public RemoveFirstCommand(CollectionController collectionController) {
        super("remove_first", "remove first element from collection");
        this.collectionController = collectionController;
    }

    /**
     * Method to complete remove_first command
     * <p>It removes first element from collection
     * <p>If collection is empty user is informed
     */
    @Override
    public void execute() {
        if(this.collectionController.getCollection().isEmpty()){
            Console.getInstance().printLn("Collection is empty!");
            return;
        }
        this.collectionController.removeFirst();
        Console.getInstance().printLn("Element removed successfully!");
    }

    /**
     * Method checks if amount arguments is correct
     * @param commandArgs String array with different arguments
     * @throws WrongAmountOfArgumentsException If number of arguments is not equal to zero
     */
    @Override
    public void initCommandArgs(String[] commandArgs) throws WrongAmountOfArgumentsException {
        if (commandArgs.length != 0) throw new WrongAmountOfArgumentsException("Wrong amount of arguments!", 0, commandArgs.length);
    }
}
