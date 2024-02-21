package ru.timur.Controllers;

import ru.timur.Commands.*;
import ru.timur.Exceptions.InvalidDataException;
import ru.timur.Exceptions.RecursiveScriptException;
import ru.timur.Exceptions.WrongAmountOfArgumentsException;
import ru.timur.Collection.Readers.WorkerReader;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Class which is used to work with UserCommand objects
 */
public class CommandsController {
    /**
     * List with all available commands
     */
    private final ArrayList<ICommand> commandsList;

    /**
     * CommandsController constructor
     * <p>Gets all required controllers and initialize all commands
     * @param collectionController
     * @param workerReader
     * @param dataFileController
     */
    public CommandsController(CollectionController collectionController, WorkerReader workerReader, DataFileController dataFileController){
        this.commandsList  = new ArrayList<>(Arrays.asList(
                new HelpCommand(this),
                new InfoCommand(collectionController),
                new ShowCommand(collectionController),
                new AddCommand(workerReader, collectionController),
                new UpdateByIdCommand(workerReader, collectionController),
                new RemoveByIdCommand(collectionController),
                new ClearCommand(collectionController),
                new SaveCommand(collectionController, dataFileController),
                new ExecuteScriptCommand(),
                new ExitCommand(collectionController),
                new RemoveFirstCommand(collectionController),
                new RemoveGreaterCommand(workerReader, collectionController),
                new RemoveLowerCommand(workerReader, collectionController),
                new MinBySalaryCommand(collectionController),
                new FilterLessThanEndDateCommand(workerReader, collectionController),
                new PrintFieldDescendingSalaryCommand(collectionController)
        ));
    }

    /**
     * Method to get list of commands
     * @return ArrayList of UserCommand
     */
    public ArrayList<ICommand> getCommandsList() {
        return commandsList;
    }

    /**
     * Method to find command by its name and init its argument
     * @param commandName Name of command to find
     * @param commandArgs Arguments of command
     * @return UserCommand object
     * @throws WrongAmountOfArgumentsException If number of arguments is wrong for given command
     * @throws NoSuchElementException If command not found
     * @throws InvalidDataException if command argument are not valid
     */
    public UserCommand launchCommand(String commandName, String[] commandArgs) throws InvalidDataException, WrongAmountOfArgumentsException, NoSuchElementException {
        if(this.commandsList.stream().noneMatch(userCommand -> userCommand.getName().equals(commandName))){
            throw new NoSuchElementException("Command '" + commandName + "' not found!");
        }

        UserCommand command;

        command = (UserCommand) this.commandsList
                .stream()
                .filter(userCommand -> userCommand.getName().equals(commandName))
                .findFirst().get();
        command.initCommandArgs(commandArgs);

        return command;
    }
}
