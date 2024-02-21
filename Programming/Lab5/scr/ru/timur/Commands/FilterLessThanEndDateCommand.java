package ru.timur.Commands;

import ru.timur.Constants;
import ru.timur.Controllers.CollectionController;
import ru.timur.Exceptions.InvalidDataException;
import ru.timur.Exceptions.WrongAmountOfArgumentsException;
import ru.timur.UI.Console;
import ru.timur.Collection.Readers.WorkerReader;

import java.time.LocalDateTime;

/**
 * Class with realization of filter_less_than_end_date command
 * <p>This command is used to print all elements whose endDate is less than given
 * @see UserCommand
 * @see ICommand
 */
public class FilterLessThanEndDateCommand extends UserCommand {
    /**
     * Worker reader which is used to read endDate
     */
    private WorkerReader workerReader;

    /**
     * Controller of collection which is used to get list of filtered elements
     */
    private CollectionController collectionController;

    /**
     * FilterLessThanEndDateCommand constructor
     * <p> Firstly it initializes super constructor by command name, arguments and description
     * @param workerReader
     * @param collectionController
     */
    public FilterLessThanEndDateCommand(WorkerReader workerReader, CollectionController collectionController) {
        super("filter_less_than_end_date", "endDate", "print all elements whose endDate is less than given");
        this.workerReader = workerReader;
        this.collectionController = collectionController;
    }

    /**
     * Method to complete filter_less_than_end_date command
     * <p>It reads endDate from user and then print all elements whose endDate is less than given
     * <p>If collection is empty endDate is not read (except script mode)
     * @throws InvalidDataException If an error occurred while reading
     */
    @Override
    public void execute() throws InvalidDataException {
        if(this.collectionController.getCollection().isEmpty()){
            Console.getInstance().printLn("Collection is empty!");
            if(Constants.SCRIPT_MODE){
                workerReader.readEndDate();
            }
            return;
        }
        LocalDateTime endDate = workerReader.readEndDate();
        Console.getInstance().printLn(this.collectionController.getLessThanEndDate(endDate));
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
