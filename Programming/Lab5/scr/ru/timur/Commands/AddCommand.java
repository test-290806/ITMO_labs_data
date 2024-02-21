package ru.timur.Commands;

import ru.timur.Collection.Worker;
import ru.timur.Controllers.CollectionController;
import ru.timur.Exceptions.InvalidDataException;
import ru.timur.Exceptions.WrongAmountOfArgumentsException;
import ru.timur.Collection.Readers.WorkerReader;
import ru.timur.UI.Console;

/**
 * Class with realization of add command
 * <p>This command is used to add new element to collection
 * @see UserCommand
 * @see ICommand
 */
public class AddCommand extends UserCommand {
    /**
     * Controller of collection which is used to add new element
     */
    private CollectionController collectionController;
    /**
     * Worker reader which is used to read new element from user
     */
    private WorkerReader workerReader;

    /**
     * AddCommand constructor
     * <p> Firstly it initializes super constructor by command name, arguments and description
     * @param workerReader
     * @param collectionController
     */
    public AddCommand(WorkerReader workerReader, CollectionController collectionController) {
        super("add", "{element}", "add new element to collection");
        this.collectionController = collectionController;
        this.workerReader = workerReader;
    }

    /**
     * Method to complete add command
     * <p>It reads new element and then adds it to collection
     * @throws InvalidDataException If an error occurred while reading
     */
    @Override
    public void execute() throws InvalidDataException {
        Worker worker = this.workerReader.readWorker();
        collectionController.add(worker);
        Console.getInstance().printLn("Worker added successfully!");
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
