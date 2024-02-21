package ru.timur.JSON;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import ru.timur.Constants;

import java.lang.reflect.Type;
import java.time.LocalDateTime;

/**
 * Custom serializer to write LocalDateTime to JSON files
 */
public class LocalDateTimeSerializer implements JsonSerializer<LocalDateTime> {
    @Override
    public JsonElement serialize(LocalDateTime localDateTime, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(Constants.formatter.format(localDateTime.toLocalDate()));
    }
}
