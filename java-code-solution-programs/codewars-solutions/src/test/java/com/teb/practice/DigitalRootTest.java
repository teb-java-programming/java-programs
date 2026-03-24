package com.teb.practice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DigitalRootTest {

    private final DigitalRoot digitalRoot = new DigitalRoot();

    @ParameterizedTest
    @CsvSource({"148, 4", "180, 9", "0, 0"})
    void testDigitalRoot(int input, int result) {

        assertEquals(result, digitalRoot.digitalRoot(input));
    }
}
