package com.teb.practice.util.structured;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.teb.practice.model.Data;

import org.junit.jupiter.api.Test;

import java.util.List;

class JsonUtilTest {

    private static final String FILE_PATH = "src/test/resources/output/json-test.json";

    private final JsonUtil jsonUtil = new JsonUtil();

    @Test
    void testWritesAndReadsJsonFile() {

        List<Data> input = List.of(new Data("Adam", 52, 44000), new Data("William", 52, 44000));

        jsonUtil.write(FILE_PATH, input);

        List<Data> output = jsonUtil.read(FILE_PATH);

        assertEquals(input, output);
    }
}
