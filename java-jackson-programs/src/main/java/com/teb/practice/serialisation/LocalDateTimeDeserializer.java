package com.teb.practice.serialisation;

import static java.time.LocalDateTime.parse;
import static java.time.format.DateTimeFormatter.ofPattern;

import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.deser.std.StdDeserializer;

import java.time.LocalDateTime;

public class LocalDateTimeDeserializer extends StdDeserializer<LocalDateTime> {

    public LocalDateTimeDeserializer() {
        super(LocalDateTime.class);
    }

    @Override
    public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext context) {

        return parse(jsonParser.getString(), ofPattern("dd-MM-yyyy HH:mm"));
    }
}
