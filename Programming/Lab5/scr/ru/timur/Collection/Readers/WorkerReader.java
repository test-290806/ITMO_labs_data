package ru.timur.Collection.Readers;

import ru.timur.Collection.*;
import ru.timur.Parsers.WorkerParsers;
import ru.timur.UI.YesNoQuestionAsker;
import ru.timur.Validators.WorkerValidators;
import ru.timur.Constants;
import ru.timur.Controllers.CollectionController;
import ru.timur.Exceptions.InvalidDataException;
import ru.timur.UI.Console;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

/**
 * Class with methods to read all fields of Worker class
 * @see Worker
 */
public class WorkerReader extends ValueReader {
    /**
     * Object of collection controller which is used to generate valid id while reading new Worker class element
     */
    private final CollectionController collectionController;
    public WorkerReader(CollectionController collectionController) {
        this.collectionController = collectionController;
    }

    /**
     * Method to read Worker from user input
     * @return Worker object
     * @throws InvalidDataException If input is wrong and script mode is on
     */
    public Worker readWorker() throws InvalidDataException {
        long id = this.collectionController.generateId();
        String name = readName();
        Coordinates coordinates = readCoordinates();
        ZonedDateTime creationDate = ZonedDateTime.now();
        Integer salary = readSalary();
        LocalDateTime startDate = readStartDate();
        LocalDateTime endDate = readEndDate();
        Status status = readStatus();
        Person person = readPerson();
        return new Worker(id, name, coordinates, creationDate, salary, startDate, endDate, status, person);
    }

    /**
     * Method to read workers name
     * @return String name
     * @throws InvalidDataException If input is wrong and script mode is on
     */
    public String readName() throws InvalidDataException {
        return readValue("name", WorkerValidators.nameValidator, WorkerParsers.stringParser);
    }

    /**
     * Method to read workers coordinates
     * @return Coordinates
     * @throws InvalidDataException If input is wrong and script mode is on
     */
    public Coordinates readCoordinates() throws InvalidDataException {
        return new Coordinates(readX(), readY());
    }

    /**
     * Method to read x coordinate
     * @return double value
     * @throws InvalidDataException If input is wrong and script mode is on
     */
    public double readX() throws InvalidDataException {
        return readValue("x coordinate", WorkerValidators.xValidator, WorkerParsers.doubleParser);
    }

    /**
     * Method to read y coordinate
     * @return double value
     * @throws InvalidDataException If input is wrong and script mode is on
     */
    public double readY() throws InvalidDataException {
        return readValue("y coordinate", WorkerValidators.yValidator, WorkerParsers.doubleParser);
    }

    /**
     * Method to read workers salary
     * @return Integer value of salary
     * @throws InvalidDataException If input is wrong and script mode is on
     */
    public Integer readSalary() throws InvalidDataException {
        return readValue("salary", WorkerValidators.salaryValidator, WorkerParsers.integerParser);
    }

    /**
     * Method to read workers start date
     * @return LocalDateTime value of start date
     * @throws InvalidDataException If input is wrong and script mode is on
     */
    public LocalDateTime readStartDate() throws InvalidDataException {
        return readValue("start date (" + Constants.DATE_FORMAT_STRING + ")", WorkerValidators.startDateValidator, WorkerParsers.localDateTimeParser);
    }

    /**
     * Method to read workers end date
     *
     * <p>Before reading user is asked if worker has endDate
     * @return LocalDateTime value of endDate or null if it is no endDate
     * @throws InvalidDataException If input is wrong and script mode is on
     */
    public LocalDateTime readEndDate() throws InvalidDataException {
        YesNoQuestionAsker questionAsker = new YesNoQuestionAsker("Does worker has end date?");
        if(!questionAsker.ask()) return null;
        return readValue("end date (" + Constants.DATE_FORMAT_STRING + ")", WorkerValidators.endDateValidator, WorkerParsers.localDateTimeParser);
    }

    /**
     * Method to read workers status
     * <p>Before reading method prints all possible values of status
     *
     * @return Status value
     * @throws InvalidDataException If input is wrong and script mode is on
     */
    public Status readStatus() throws InvalidDataException {
        if(!Constants.SCRIPT_MODE) {
            Console.getInstance().printLn("List of possible status values:");
            for (Status i : Status.values()) {
                Console.getInstance().printLn(i);
            }
        }
        return readValue("status", WorkerValidators.statusValidator, WorkerParsers.statusParser);
    }

    /**
     * Method to read workers person
     * <p>Before reading user is asked if worker has person
     * @return Person value or null if worker doesn't have person
     * @throws InvalidDataException If input is wrong and script mode is on
     */
    public Person readPerson() throws InvalidDataException {
        YesNoQuestionAsker questionAsker = new YesNoQuestionAsker("Does worker has person?");
        if(!questionAsker.ask()) return null;
        return new Person(readHeight(), readEyeColor(), readNationality());
    }

    /**
     * Method to read persons height
     * @return Long value of height
     * @throws InvalidDataException If input is wrong and script mode is on
     */
    public long readHeight() throws InvalidDataException {
        return readValue("height", WorkerValidators.heightValidator, WorkerParsers.longParser);
    }

    /**
     * Method to read persons eye color
     * <p>Before reading method asks if person has eye color and if answer is yes all possible Colors are printed
     * @return Color value
     * @throws InvalidDataException If input is wrong and script mode is on
     */
    public Color readEyeColor() throws InvalidDataException {
        YesNoQuestionAsker questionAsker = new YesNoQuestionAsker("Does person has eye color?");
        if(!questionAsker.ask()) return null;
        if(!Constants.SCRIPT_MODE) {
            Console.getInstance().printLn("List of possible eye color values:");
            for (Color i : Color.values()) {
                Console.getInstance().printLn(i);
            }
        }
        return readValue("eye color", WorkerValidators.eyeColorValidator, WorkerParsers.eyeColorParser);
    }

    /**
     * Method to read persons nationality
     * <p>Before reading method asks if person has nationality and if answer is yes all possible Countries are printed
     * @return Country value
     * @throws InvalidDataException If input is wrong and script mode is on
     */
    public Country readNationality() throws InvalidDataException {
        YesNoQuestionAsker questionAsker = new YesNoQuestionAsker("Does person has nationality?");
        if(!questionAsker.ask()) return null;
        if(!Constants.SCRIPT_MODE) {
            Console.getInstance().printLn("List of possible nationality values:");
            for (Country i : Country.values()) {
                Console.getInstance().printLn(i);
            }
        }
        return readValue("nationality", WorkerValidators.nationalityValidator, WorkerParsers.nationalityParser);
    }
}