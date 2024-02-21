package ru.timur.Commands;

import ru.timur.Controllers.CollectionController;
import ru.timur.Exceptions.InvalidDataException;
import ru.timur.Exceptions.WrongAmountOfArgumentsException;
import ru.timur.UI.Console;

import java.util.NoSuchElementException;

/**
 * Class with realization of min_by_salary command
 * <p>This command is used to print any element from collection which salary field is minimal
 * @see UserCommand
 * @see ICommand
 */
public class MinBySalaryCommand extends UserCommand {
    /**
     * Controller of collection which is used to get required element
     */
    private CollectionController collectionController;

    /**
     * MinBySalaryCommand constructor
     * <p> Firstly it initializes super constructor by command name, arguments and description
     * @param collectionController
     */
    public MinBySalaryCommand(CollectionController collectionController) {
        super("min_by_salary", "print any element from collection which salary field is minimal");
        this.collectionController = collectionController;
    }

    /**
     * Method to complete min_by_salary command
     * <p>It prints element with minimal salary
     * <p>If collection is empty user is informed
     */
    @Override
    public void execute() {
        if(this.collectionController.getCollection().isEmpty()){
            Console.getInstance().printLn("Collection is empty!");
            return;
        }
        Console.getInstance().printLn(this.collectionController.getMinBySalary());
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
