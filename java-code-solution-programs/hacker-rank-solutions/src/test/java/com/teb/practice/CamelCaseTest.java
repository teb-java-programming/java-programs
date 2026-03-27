package com.teb.practice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CamelCaseTest {

    private final CamelCase camelCase = new CamelCase();

    @ParameterizedTest
    @CsvSource({"camelCase, 2", "camelcase, 1"})
    void testCamelCase(String input, int result) {

        assertEquals(result, camelCase.checkCamelCase(input));
    }

    @ParameterizedTest
    @CsvSource({"CamelCase", "CAMELCASE"})
    void testThrowsExceptionWhenNotCamelCase(String input) {

        assertThrows(RuntimeException.class, () -> camelCase.checkCamelCase(input));
    }
}
