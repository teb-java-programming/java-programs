package com.teb.practice.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class EvenOddNumberTest {

    private final EvenOddNumber evenOddNumber = new EvenOddNumber();

    @ParameterizedTest
    @CsvSource({"8, Even", "11, Odd", "-22, Even", "-5, Odd"})
    void testEvenOrOdd(int input, String result) {

        assertEquals(result, evenOddNumber.findEvenOdd(input));
    }

    @Test
    void testThrowsExceptionForInvalidInput() {

        Exception e = assertThrows(RuntimeException.class, () -> evenOddNumber.findEvenOdd(0));
        assertInstanceOf(ArithmeticException.class, e);
    }
}
