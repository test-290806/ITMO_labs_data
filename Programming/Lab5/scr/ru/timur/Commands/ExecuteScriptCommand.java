package ru.timur.Commands;

import ru.timur.Constants;
import ru.timur.Controllers.FileLoader;
import ru.timur.Exceptions.RecursiveScriptException;
import ru.timur.Exceptions.WrongAmountOfArgumentsException;
import ru.timur.Main;
import ru.timur.UI.Console;

import java.io.*;
import java.util.Scanner;

/**
 * Class with realization of execute_script command
 * <p>This command is used to execute script file with commands
 * @see UserCommand
 * @see ICommand
 */
public class ExecuteScriptCommand extends UserCommand {
    /**
     * Path to script file
     */
    private String scriptFilePath;

    /**
     *  ExecuteScriptCommand constructor
     * <p> Firstly it initializes super constructor by command name, arguments and description
     */
    public ExecuteScriptCommand() {
        super("execute_script", "file_name", "read and execute script from given file");
    }

    /**
     * Method to complete execute_script command
     * <p>Firstly it completes validation of path to script file
     * <p>Than file is checked to recursive script (stack of script files is used
     * <p>Eventually it sets script mode, changes Console inputStream to scriptFile and calls scriptMode
     * <p>Regardless of the result of the script execution Script mode is removed and Console inputString is returned to previous value
     * @throws Exception If any error occurred while executing script
     */
    @Override
    public void execute() throws Exception {

        File scriptFile = new FileLoader().loadFile(scriptFilePath, "txt", "r", "Script file");

        if(!Constants.scriptStack.isEmpty() && Constants.scriptStack.contains(scriptFilePath)){
            throw new RecursiveScriptException("Script is recursive!");
        }

        Constants.scriptStack.push(scriptFilePath);

        Scanner prevScanner = Console.getInstance().getScanner();
        Console.getInstance().setScanner(new Scanner(new FileInputStream(scriptFile)));

        Constants.SCRIPT_MODE = true;

        try {
            Main.scriptMode();
            Console.getInstance().printLn("Script executed successfully!");
        } finally {
            Constants.scriptStack.pop();
            Constants.SCRIPT_MODE = false;
            Console.getInstance().setScanner(prevScanner);
        }
    }

    /**
     * Method checks if amount arguments is correct
     * @param commandArgs String array with different arguments
     * @throws WrongAmountOfArgumentsException If number of arguments is not equal to zero
     */
    @Override
    public void initCommandArgs(String[] commandArgs) throws WrongAmountOfArgumentsException {
        if(commandArgs.length != 1) throw new WrongAmountOfArgumentsException("Wrong amount of arguments!", 1, commandArgs.length);
        this.scriptFilePath = commandArgs[0];
    }
}
