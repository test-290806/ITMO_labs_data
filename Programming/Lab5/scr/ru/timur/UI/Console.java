package ru.timur.UI;

import java.util.Scanner;

/**
 * Singleton class to handle input and output
 */
public class Console {
    private static Console CONSOLE;
    /**
     * Scanner to handle user input
     */
    private Scanner scanner;
    private Console(){}

    /**
     * Returns CONSOLE
     * <p>If CONSOLE is null it is initialized
     * @return CONSOLE
     */
    public static Console getInstance(){
        if(CONSOLE == null){
            CONSOLE = new Console();
        }
        return CONSOLE;
    }

    /**
     * Method to set Console scanner
     * @param scanner new scanner
     */
    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Method to get Console scanner
     * @return scanner
     */
    public Scanner getScanner() {
        return scanner;
    }

    /**
     * Method to read next line from input stream
     * @return String line
     */
    public String readLine(){
        return scanner.nextLine();
    }

    /**
     * Method to check if input stream has next line
     * @return true if it has, false otherwise
     */
    public boolean hasNextLine(){
        return scanner.hasNextLine();
    }

    /**
     * Method to print data to user output
     * @param o Object to print
     */
    public void print(Object o){
        System.out.print(o);
    }

    /**
     * Method to print data to user output with \n in the end
     * @param o Object to print
     */
    public void printLn(Object o){
        System.out.println(o);
    }
    /**
     * Method to print error message to user
     * @param error Message to print
     */
    public void printError(String error){
        System.err.println(error);
    }
}
