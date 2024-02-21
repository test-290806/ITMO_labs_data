package ru.timur.Collection.Readers;

import ru.timur.Parsers.Parser;
import ru.timur.Validators.Validator;
import ru.timur.Constants;
import ru.timur.Exceptions.InvalidDataException;
import ru.timur.UI.Console;

/**
 * Abstract class to read any value from user
 * <p>It validates input data and keep asking user until input is correct
 */
public abstract class ValueReader {
    /**
     * Method to read value from user with validation
     *
     * @param valueName Name of value to print promt to enter
     * @param validator Functional interface which validate value to check if it is correct
     * @param parser Functional interface which parse value from string
     * @return Value which was read
     * @param <T> Type of value
     * @throws InvalidDataException If input value is wrong in ScriptMode is on
     * @see Validator
     * @see Parser
     */
    public <T> T readValue(String valueName, Validator<T> validator, Parser<T> parser) throws InvalidDataException {
        T value;
        while (true) {
            if(!Constants.SCRIPT_MODE) Console.getInstance().print("Enter " + valueName + ": ");
            String s = Console.getInstance().readLine().trim();
            try {
                value = s.isEmpty() ? null : parser.parse(s);
                validator.validate(value);
                break;
            } catch (InvalidDataException e){
                if(Constants.SCRIPT_MODE) throw e;
                else{
                    Console.getInstance().printLn(e.getMessage());
                }
            }
        }
        return value;
    }
}
