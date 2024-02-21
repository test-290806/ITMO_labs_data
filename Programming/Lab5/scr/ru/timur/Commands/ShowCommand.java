package ru.timur.Commands;

import ru.timur.Controllers.CollectionController;
import ru.timur.Exceptions.InvalidDataException;
import ru.timur.Exceptions.WrongAmountOfArgumentsException;
import ru.timur.UI.Console;

/**
 * Class with realization of show command
 * <p>This command is used to print all elements of collection
 * @see UserCommand
 * @see ICommand
 */
public class ShowCommand extends UserCommand {
    /**
     * Controller of collection which is used to get collection
     */
    private CollectionController collectionController;

    /**
     * ShowCommand constructor
     * <p> Firstly it initializes super constructor by command name, arguments and description
     * @param collectionController
     */
    public ShowCommand(CollectionController collectionController) {
        super("show", "print all elements of collection");
        this.collectionController = collectionController;
    }

    /**
     * Method to complete show command
     * <p>It gets collection from collection controller and then prints it
     * <p>If collection is empty user is informed
     */
    @Override
    public void execute() {
        if(this.collectionController.getCollection().isEmpty()){
            Console.getInstance().printLn("Collection is empty");
            return;
        }
        this.collectionController.getCollection()
                .forEach(worker -> Console.getInstance().printLn(worker));
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
