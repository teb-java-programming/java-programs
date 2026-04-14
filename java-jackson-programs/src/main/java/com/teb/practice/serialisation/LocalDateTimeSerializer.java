package com.teb.practice.serialisation;

import static java.time.format.DateTimeFormatter.ofPattern;

import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ser.std.StdSerializer;

import java.time.LocalDateTime;

public class LocalDateTimeSerializer extends StdSerializer<LocalDateTime> {

    public LocalDateTimeSerializer() {
        super(LocalDateTime.class);
    }

    @Override
    public void serialize(
            LocalDateTime localDateTime, JsonGenerator generator, SerializationContext context)
            throws JacksonException {

        generator.writeString(localDateTime.format(ofPattern("dd-MM-yyyy HH:mm")));
    }
}
