package com.teb.practice.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LucasNumberTest {

    private final LucasNumber lucasNumber = new LucasNumber();

    @ParameterizedTest
    @CsvSource({"0, 2", "1, 1", "3, 4", "6, 18", "-4, 7", "-9, -76"})
    void testLucasNumber(int input, int result) {

        assertEquals(result, lucasNumber.findLucasNumber(input));
    }
}
