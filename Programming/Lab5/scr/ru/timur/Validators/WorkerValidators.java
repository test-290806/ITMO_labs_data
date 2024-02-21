package ru.timur.Validators;

import ru.timur.Collection.*;
import ru.timur.Exceptions.InvalidDataException;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

/**
 * Class which contains validators for all fields of Worker
 * <p>All validators realize constraints given by task
 */
public class WorkerValidators {
    public static Validator<Worker> workerValidator = new Validator<Worker>() {
        @Override
        public void validate(Worker value) throws InvalidDataException {
            if(value == null) throw new InvalidDataException("Worker can't be empty!");
            idValidator.validate(value.getId());
            nameValidator.validate(value.getName());
            coordinatesValidator.validate(value.getCoordinates());
            creationDateValidator.validate(value.getCreationDate());
            salaryValidator.validate(value.getSalary());
            startDateValidator.validate(value.getStartDate());
            endDateValidator.validate(value.getEndDate());
            statusValidator.validate(value.getStatus());
            personValidator.validate(value.getPerson());
        }
    };
    public static Validator<Long> idValidator = new Validator<Long>() {
        @Override
        public void validate(Long value) throws InvalidDataException {
            if(value == null) throw new InvalidDataException("Id can't be empty!");
            if(value <= 0) throw new InvalidDataException("Id must be greater than zero!");
        }
    };
    public static Validator<String> nameValidator = new Validator<String>() {
        @Override
        public void validate(String value) throws InvalidDataException {
            if(value == null || value.isEmpty()) throw new InvalidDataException("Name can't be empty!");
            if(value.contains(" ")) throw new InvalidDataException("Name can't contain spaces!");
        }
    };
    public static Validator<Coordinates> coordinatesValidator = new Validator<Coordinates>() {
        @Override
        public void validate(Coordinates value) throws InvalidDataException {
            if(value == null) throw new InvalidDataException("Coordinates can't be empty!");
            xValidator.validate(value.getX());
            yValidator.validate(value.getY());
        }
    };
    public static Validator<Double> xValidator = new Validator<Double>() {
        @Override
        public void validate(Double value) throws InvalidDataException {
            if(value == null) throw new InvalidDataException("X can't be empty!");
            if(value > 657) throw new InvalidDataException("x coordinate max value is 657");
        }
    };
    public static Validator<Double> yValidator = new Validator<Double>() {
        @Override
        public void validate(Double value) throws InvalidDataException {
            if(value == null) throw new InvalidDataException("Y can't be empty!");
        }
    };
    public static Validator<LocalDateTime> startDateValidator = new Validator<LocalDateTime>() {
        @Override
        public void validate(LocalDateTime value) throws InvalidDataException {
            if(value == null) throw new InvalidDataException("Start date can't be empty!");
        }
    };
    public static Validator<LocalDateTime> endDateValidator = new Validator<LocalDateTime>() {
        @Override
        public void validate(LocalDateTime value) throws InvalidDataException {
            if(value == null) return;
        }
    };
    public static Validator<ZonedDateTime> creationDateValidator = new Validator<ZonedDateTime>() {
        @Override
        public void validate(ZonedDateTime value) throws InvalidDataException {
            if(value == null) throw new InvalidDataException("Creation date can't be empty!");
        }
    };
    public static Validator<Integer> salaryValidator = new Validator<Integer>() {
        @Override
        public void validate(Integer value) throws InvalidDataException {
            if(value == null) throw new InvalidDataException("Salary can't be empty!");
            if(value <= 0) throw new InvalidDataException("Salary must be greater than zero!");
        }
    };
    public static Validator<Status> statusValidator = new Validator<Status>() {
        @Override
        public void validate(Status value) throws InvalidDataException {
            if(value == null) throw new InvalidDataException("Status can't be empty!");
        }
    };
    public static Validator<Person> personValidator = new Validator<Person>() {
        @Override
        public void validate(Person value) throws InvalidDataException {
            if(value == null) return;
            heightValidator.validate(value.getHeight());
            eyeColorValidator.validate(value.getEyeColor());
            nationalityValidator.validate(value.getNationality());
        }
    };
    public static Validator<Long> heightValidator = new Validator<Long>() {
        @Override
        public void validate(Long value) throws InvalidDataException {
            if(value == null) throw new InvalidDataException("Height can't be empty!");
            if(value <= 0) throw new InvalidDataException("Height must be greater than zero!");
        }
    };
    public static Validator<Color> eyeColorValidator = new Validator<Color>() {
        @Override
        public void validate(Color value) throws InvalidDataException {
            if(value == null) return;
        }
    };
    public static Validator<Country> nationalityValidator = new Validator<Country>() {
        @Override
        public void validate(Country value) throws InvalidDataException {
            if(value == null) return;
        }
    };
}
