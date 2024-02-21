package ru.timur.JSON;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import ru.timur.Constants;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * Custom deserializer to parse ZonedDateTime from JSON files
 */
public class ZonedDateTimeDeserializer implements JsonDeserializer<ZonedDateTime> {
    @Override
    public ZonedDateTime deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return ZonedDateTime.of(LocalDateTime.parse(jsonElement.getAsString(), Constants.formatter), ZoneId.of("Europe/Moscow"));
    }
}
