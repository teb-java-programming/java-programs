package com.teb.practice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class StringToIntegerExceptionTest {

    private final StringToIntegerException stringToIntegerException =
            new StringToIntegerException();

    @ParameterizedTest
    @CsvSource({"-14, -14", "4, 4", "17, 17", "76, 76", "-101, -101"})
    void testConversion(String input, int result) {

        assertEquals(result, stringToIntegerException.convertStringToInteger(input));
    }

    @Test
    void testThrowsExceptionWhenStringCannotBeConverted() {

        Exception e =
                assertThrows(
                        RuntimeException.class,
                        () -> stringToIntegerException.convertStringToInteger("Integer"));
        assertInstanceOf(NumberFormatException.class, e);
    }
}
