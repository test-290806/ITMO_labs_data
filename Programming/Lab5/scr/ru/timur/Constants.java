package ru.timur;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Stack;

/**
 * This class is made to store constants which are required in many parts of project
 */
public class Constants {
    /**
     * Flag of script mode
     */
    public static boolean SCRIPT_MODE = false;
    /**
     * Global format for date
     */
    public static final String DATE_FORMAT_STRING = "dd.MM.yyyy";
    /**
     * Global format for time
     */
    public static final String TIME_FROMAT_STRING = "HH:mm";
    /**
     * Global universal formatter for DateTime objects
     * <p>If time is not given it is set to 00:00
     */
    public static final DateTimeFormatter formatter = new DateTimeFormatterBuilder()
            .appendPattern(DATE_FORMAT_STRING)
            .optionalStart()
            .appendPattern(" " + TIME_FROMAT_STRING)
            .optionalEnd()
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
            .toFormatter();
    /**
     * Global stack for script file names
     * <p>It is used to track recursion of scripts
     */
    public static Stack<String> scriptStack = new Stack<>();
}
