package com.teb.practice.config;

import tools.jackson.databind.json.JsonMapper;

public class JacksonConfig {

    public static JsonMapper createJsonMapper() {

        return JsonMapper.builder().build();
    }
}
