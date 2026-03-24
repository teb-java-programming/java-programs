package com.teb.practice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class NumberPlayTest {

    private static final int START = 9;
    private static final int LIMIT = 16;

    private final NumberPlay numberPlay = new NumberPlay();

    @ParameterizedTest
    @CsvSource({"+, 65", "*, 328185"})
    void testEvenNumberAction(String action, int result) {

        assertEquals(result, numberPlay.evenNumberAction(START, LIMIT, action));
    }

    @Test
    void testEvenNumberActionThrowsException() {

        assertThrows(RuntimeException.class, () -> numberPlay.evenNumberAction(START, LIMIT, "-"));
    }

    @Test
    void testFactorial() {

        assertEquals(20922789888000L, numberPlay.factorial(LIMIT));
    }

    @ParameterizedTest
    @CsvSource({"12, 144", "1, 1", "0, 0"})
    void testFibonacci(int limit, int result) {

        assertEquals(result, numberPlay.fibonacci(limit));
    }

    @ParameterizedTest
    @CsvSource({"0, 2", "1, 1", "3, 4", "6, 18", "-4, 7", "-9, -76"})
    void testLucasNumber(int input, int result) {

        assertEquals(result, numberPlay.lucasNumber(input));
    }

    @ParameterizedTest
    @CsvSource({"9, 16, 1", "24, 6, 6", "5, 40, 5", "-11, -19, 1"})
    void testGreatestCommonFactor(int inputOne, int inputTwo, int result) {

        assertEquals(result, numberPlay.greatestCommonFactor(inputOne, inputTwo));
    }

    @Test
    void testLowestCommonMultiple() {

        assertEquals(144, numberPlay.lowestCommonMultiple(START, LIMIT));
    }
}
