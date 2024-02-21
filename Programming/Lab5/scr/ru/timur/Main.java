package ru.timur;

import ru.timur.Collection.Readers.WorkerReader;
import ru.timur.Collection.Worker;
import ru.timur.Commands.UserCommand;
import ru.timur.Controllers.CollectionController;
import ru.timur.Controllers.CommandsController;
import ru.timur.Controllers.DataFileController;
import ru.timur.Controllers.FileLoader;
import ru.timur.UI.Console;

import java.io.*;
import java.util.*;

/**
 * Main app class
 * <p>Completes initialization of all controllers, sets default input stream for Console
 * <p>In the beginning loads data file (if it is wrong program stops), then calls interactiveMode method
 */
public class Main {
    /**
     * Controller of collection
     */
    private static CollectionController collectionController;
    /**
     * Reader of data elements
     */
    private static WorkerReader workerReader;
    /**
     * Controller of commands
     */
    private static CommandsController commandsController;
    /**
     * Controller of data file
     */
    private static DataFileController dataFileController;

    /**
     * Main method of program
     * <p>Calls methods to load data file, init all controllers and start handling user commands
     * @param args (not used)
     */
    public static void main(String[] args) {
        Console.getInstance().setScanner(new Scanner(System.in));
        collectionController = new CollectionController(loadData());
        workerReader = new WorkerReader(collectionController);
        commandsController = new CommandsController(collectionController, workerReader, dataFileController);
        interactiveMode();
    }

    /**
     * method which is used to work with script file
     * @throws Exception if any error occurred in process of executing
     */
    public static void scriptMode() throws Exception {
        while(Console.getInstance().hasNextLine()) {
            String s = Console.getInstance().readLine();
            String[] input = (s.trim() + " ").split(" ");
            if(input.length == 0) continue;
            String commandName = input[0];
            Console.getInstance().printLn(commandName);
            String[] commandArgs = Arrays.copyOfRange(input, 1, input.length);
            UserCommand command = commandsController.launchCommand(commandName, commandArgs);
            command.execute();
        }
    }

    /**
     * Method to handle user input
     *
     * <p>Reads commands from user, gets their name and arguments, launch command and execute it
     * <p>If any error is occurred method prints error message and continues to read data
     */
    public static void interactiveMode(){
        while(Console.getInstance().hasNextLine()) {
            String s = Console.getInstance().readLine();
            String[] input = (s.trim() + " ").split(" ");
            if(input.length == 0) continue;
            String commandName = input[0];
            String[] commandArgs = Arrays.copyOfRange(input, 1, input.length);

            try {
                UserCommand command = commandsController.launchCommand(commandName, commandArgs);
                command.execute();
            }
            catch (Exception e){
                Console.getInstance().printError(e.getMessage());
            }
        }
    }

    private static String readFileName(){
        Console.getInstance().print("Enter environmental variable name: ");
        String envName = Console.getInstance().readLine().trim();
        String dataFilePath = System.getenv(envName);
        if(dataFilePath == null){
            Console.getInstance().printError("Environmental variable is not defined!");
            System.exit(0);
        }
        return dataFilePath;
    }

    /**
     * Method to load collection from data file.
     * <p>Method also completes validation of filePath and collection inside dataFile
     * @return Collection of workers
     * @see DataFileController
     * @see CollectionController
     */
    private static PriorityQueue<Worker> loadData(){
        String dataFilePath = readFileName();

        PriorityQueue<Worker> data = null;
        File dataFile = null;

        try {
            dataFile = new FileLoader().loadFile(dataFilePath, "json", "rw", "data file");
        } catch (FileNotFoundException e) {
            Console.getInstance().printError(e.getMessage());
            System.exit(0);
        }

        dataFileController = new DataFileController(dataFile);

        try {
            data = dataFileController.readJSON();
        } catch (Exception e) {
            Console.getInstance().printError("Data file reading error!");
            System.exit(0);
        }
        if(data == null) data = new PriorityQueue<>();
        if(!CollectionController.isValid(data)){
            Console.getInstance().printError("Data file is not valid!");
            System.exit(0);
        }
        Console.getInstance().printLn("Data loaded successfully!");
        return data;
    }
}