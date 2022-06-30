package kz.halykacademy.restfulhw.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateSerializer implements JsonSerializer<LocalDate> {

    @Override
    public JsonElement serialize(LocalDate creationDate, Type srcType, JsonSerializationContext context) {
        return new JsonPrimitive(DateTimeFormatter.ofPattern("d-MMM-yyyy").format(creationDate));
    }
}
