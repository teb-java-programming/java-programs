package com.teb.practice.config;

import com.teb.practice.serialisation.LocalDateTimeDeserializer;
import com.teb.practice.serialisation.LocalDateTimeSerializer;

import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.databind.module.SimpleModule;
import tools.jackson.datatype.jsr310.JavaTimeModule;

import java.time.LocalDateTime;

public class JacksonTestConfig {

    public static ObjectMapper createObjectMapper() {

        return JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .addModule(
                        new SimpleModule()
                                .addSerializer(LocalDateTime.class, new LocalDateTimeSerializer())
                                .addDeserializer(
                                        LocalDateTime.class, new LocalDateTimeDeserializer()))
                .build();
    }
}
