package com.teb.practice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.InputMismatchException;

class NegativeNumberExceptionTest {

    private final NegativeNumberException negativeNumberException = new NegativeNumberException();

    @ParameterizedTest
    @CsvSource({"5, 5, 3125", "12, 5, 248832", "64, 4, 16777216", "0, 0, 1"})
    void testPower(int n, int p, int result) {

        assertEquals(result, negativeNumberException.power(n, p));
    }

    @ParameterizedTest
    @CsvSource({"-8, 7", "9, -4", "-11, -5"})
    void testThrowsExceptionWhenEitherNumberIsNegative(int n, int p) {

        Exception e =
                assertThrows(RuntimeException.class, () -> negativeNumberException.power(n, p));
        assertInstanceOf(InputMismatchException.class, e);
        assertTrue(e.getMessage().contains("should be non-negative"));
    }
}
