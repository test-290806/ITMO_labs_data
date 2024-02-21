package ru.timur.Commands;

import ru.timur.Controllers.CollectionController;
import ru.timur.Controllers.DataFileController;
import ru.timur.Exceptions.InvalidDataException;
import ru.timur.Exceptions.WrongAmountOfArgumentsException;
import ru.timur.UI.Console;

import java.io.IOException;

/**
 * Class with realization of save command
 * <p>This command is used to save collection to data file
 * @see UserCommand
 * @see ICommand
 */
public class SaveCommand extends UserCommand {
    /**
     * Controller of collection which is used to get collection
     */
    private CollectionController collectionController;
    /**
     * Controller of data file which is used to write data
     */
    private DataFileController dataFileController;

    /**
     * SaveCommand constructor
     * <p> Firstly it initializes super constructor by command name, arguments and description
     * @param collectionController
     * @param dataFileController
     */
    public SaveCommand(CollectionController collectionController, DataFileController dataFileController) {
        super("save", "save collection to data file");
        this.collectionController = collectionController;
        this.dataFileController = dataFileController;
    }

    /**
     * Method to complete save command
     * <p>It gets current collection from collection controller and writes it to data file
     * <p>Also ChangeFlag is set to false
     * @throws IOException If an error occurred while writing to file
     */
    @Override
    public void execute() throws IOException {
        try {
            this.dataFileController.writeToJSON(this.collectionController.getCollection());
            this.collectionController.removeChangeFlag();
            Console.getInstance().printLn("Collection saved successfully!");
        } catch (IOException e) {
            throw new IOException("An error occurred while writing to the file!");
        }
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
