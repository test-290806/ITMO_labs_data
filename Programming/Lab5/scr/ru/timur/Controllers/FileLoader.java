package ru.timur.Controllers;

import ru.timur.Exceptions.WrongFilePermissionsException;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Class to load file with required parameters and generate detailed exceptions
 */
public class FileLoader {

    public FileLoader(){}

    /**
     * Method to load file by its path
     * @param filePath Path to file
     * @param extension Required file extension
     * @param permissions Required permissions of file in format:
     *                    <p> r - readOnly
     *                    <p> w - writeOnly
     *                    <p> rw - read and write
     * @param fileName Name of file to create correct exception messages
     * @return File object
     * @throws FileNotFoundException If required constraints are not completed
     */
    public File loadFile(String filePath, String extension, String permissions, String fileName) throws FileNotFoundException {
        Path path = Paths.get(filePath);
        if(!Files.exists(path)){
            throw new FileNotFoundException(String.format("%s does not exist!", fileName));
        }
        if(Files.isDirectory(path)){
            throw new FileNotFoundException("Given path is a directory!");
        }
        if(!filePath.endsWith(String.format(".%s", extension))){
            throw new FileNotFoundException(String.format("%s must be .%s!", fileName, extension));
        }
        if(permissions.contains("r") && !Files.isReadable(path)){
            throw new WrongFilePermissionsException(String.format("Wrong permissions! %s is not readable!", fileName));
        }
        if(permissions.contains("w") && !Files.isWritable(path)){
            throw new WrongFilePermissionsException(String.format("Wrong permissions! %s is not writeable!", fileName));
        }
        return new File(filePath);
    }
}
