package ru.timur.Commands;

import ru.timur.Controllers.CollectionController;
import ru.timur.Exceptions.InvalidDataException;
import ru.timur.Exceptions.WrongAmountOfArgumentsException;
import ru.timur.UI.Console;
import ru.timur.UI.YesNoQuestionAsker;

/**
 * Class with realization of exit command
 * <p>This command is used to finish program
 * @see UserCommand
 * @see ICommand
 */
public class ExitCommand extends UserCommand {
    /**
     * Controller of collection which is used to check if collection was changed since last saving
     */
    private CollectionController collectionController;

    /**
     * ExitCommand constructor
     * <p> Firstly it initializes super constructor by command name, arguments and description
     * @param collectionController
     */
    public ExitCommand(CollectionController collectionController) {
        super("exit", "stop program without saving collection");
        this.collectionController = collectionController;
    }

    /**
     * Method to complete exit command
     * <p>It checks if collection was changed after last save and tell user if it wasn't
     * <p>After this it asks user if he really wants to exit
     */
    @Override
    public void execute() {
        if(this.collectionController.wasChanged()) {
            Console.getInstance().printLn("Last changes in collection aren't saved!");
        }
        YesNoQuestionAsker questionAsker = new YesNoQuestionAsker("Do you want to exit?");
        if(questionAsker.ask()) {
            System.exit(0);
        }
    }

    /**
     * Method checks if amount arguments is correct
     * @param commandArgs String array with different arguments
     * @throws WrongAmountOfArgumentsException If number of arguments is not equal to zero
     */
    @Override
    public void initCommandArgs(String[] commandArgs) throws WrongAmountOfArgumentsException {
        if(commandArgs.length != 0){
            throw new WrongAmountOfArgumentsException("Wrong amount of arguments!", 0, commandArgs.length);
        }
    }
}
