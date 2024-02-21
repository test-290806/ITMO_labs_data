package ru.timur.JSON;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import ru.timur.Constants;

import java.lang.reflect.Type;
import java.time.ZonedDateTime;

/**
 * Custom serializer to write ZonedDateTime to JSON files
 */
public class ZonedDateTimeSerializer implements JsonSerializer<ZonedDateTime> {
    @Override
    public JsonElement serialize(ZonedDateTime zonedDateTime, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(Constants.formatter.format(zonedDateTime));
    }
}
