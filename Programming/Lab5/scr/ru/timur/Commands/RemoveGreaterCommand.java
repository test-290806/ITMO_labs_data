package ru.timur.Commands;

import ru.timur.Collection.Worker;
import ru.timur.Constants;
import ru.timur.Controllers.CollectionController;
import ru.timur.Exceptions.InvalidDataException;
import ru.timur.Collection.Readers.WorkerReader;
import ru.timur.Exceptions.WrongAmountOfArgumentsException;
import ru.timur.UI.Console;

import java.util.NoSuchElementException;

/**
 * Class with realization of remove_greater command
 * <p>This command is used to remove all elements which are greater than given
 * @see UserCommand
 * @see ICommand
 */
public class RemoveGreaterCommand extends UserCommand {
    /**
     * Worker reader which is used to read element from user
     */
    private WorkerReader workerReader;

    /**
     * Controller of collection which is used to remove elements
     */
    private CollectionController collectionController;

    /**
     * RemoveGreaterCommand constructor
     * <p> Firstly it initializes super constructor by command name, arguments and description
     * @param workerReader
     * @param collectionController
     */
    public RemoveGreaterCommand(WorkerReader workerReader, CollectionController collectionController) {
        super("remove_greater", "{element}", "remove all elements which are greater than given");
        this.workerReader = workerReader;
        this.collectionController = collectionController;
    }

    /**
     * Method to complete remove_greater command
     * <p>It reads element to compare with and then removes elements which are greater that it
     * <p>In the end it prints number of deleted elements
     * <p>If collection is empty element is not read (except script mode)
     * @throws InvalidDataException If an error occurred while reading
     */
    @Override
    public void execute() throws InvalidDataException {
        if(this.collectionController.getCollection().isEmpty()){
            Console.getInstance().printLn("Collection is empty!");
            if(Constants.SCRIPT_MODE){
                workerReader.readWorker();
            }
            return;
        }

        Worker worker = this.workerReader.readWorker();
        int elementsRemoved = this.collectionController.removeGreater(worker);
        Console.getInstance().printLn(String.format("Successfully removed %d elements!", elementsRemoved));
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
