package ru.timur.JSON;

import com.google.gson.*;
import ru.timur.Constants;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Custom deserializer to parse LocalDateTime from JSON files
 */
public class LocalDateTimeDeserializer implements JsonDeserializer<LocalDateTime> {
    @Override
    public LocalDateTime deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return LocalDateTime.parse(jsonElement.getAsString(), Constants.formatter);
    }
}
