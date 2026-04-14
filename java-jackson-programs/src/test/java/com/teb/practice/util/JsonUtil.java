package com.teb.practice.util;

import static com.teb.practice.config.JacksonTestConfig.createObjectMapper;

import tools.jackson.databind.ObjectMapper;

public class JsonUtil {

    private final ObjectMapper objectMapper;

    public JsonUtil() {
        this.objectMapper = createObjectMapper();
    }

    public String toJson(Object object) {

        return toPrettyJson(object);
    }

    public String toPrettyJson(Object object) {

        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
    }

    public <T> T fromJson(String json, Class<T> clazz) {

        return objectMapper.readValue(json, clazz);
    }

    public ObjectMapper objectMapper() {

        return objectMapper;
    }
}
