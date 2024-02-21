package ru.timur.Controllers;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import ru.timur.Collection.Worker;
import ru.timur.JSON.*;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.PriorityQueue;

/**
 * Class to operate with data file
 */
public class DataFileController {
    /**
     * Path to data file
     */
    private final File dataFile;
    /**
     * Gson object to operate with JSON data file
     */
    private final Gson gson;

    /**
     * DataFileController constructor
     * <p>Validate path to dataFile and initialize Gson
     * @param dataFile file with data
     */
    public DataFileController(File dataFile) {
        this.dataFile = dataFile;

        GsonBuilder gsonBuilder = new GsonBuilder();

        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer());
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer());
        gsonBuilder.registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeSerializer());
        gsonBuilder.registerTypeAdapter(ZonedDateTime.class, new ZonedDateTimeDeserializer());

        this.gson = gsonBuilder.serializeNulls().setPrettyPrinting().create();
    }

    /**
     * Method to write collection to dataFile
     * @param data collection to write
     * @throws IOException If any error occurred while writing
     */
    public void writeToJSON(PriorityQueue<Worker> data) throws IOException {
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(dataFile));
        Type dataType = new TypeToken<PriorityQueue<Worker>>(){}.getType();
        String output = this.gson.toJson(data, dataType);
        outputStreamWriter.write(output);
        outputStreamWriter.flush();
        outputStreamWriter.close();
    }

    /**
     * Method to read collection  from data file
     * @return collection
     * @throws IOException If any error occurred while reading
     * @throws JsonParseException If it is impossible to deserialize file
     */
    public PriorityQueue<Worker> readJSON() throws IOException, JsonParseException {
        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(dataFile));
        Type dataType = new TypeToken<PriorityQueue<Worker>>(){}.getType();
        return this.gson.fromJson(new JsonReader(inputStreamReader), dataType);
    }
}