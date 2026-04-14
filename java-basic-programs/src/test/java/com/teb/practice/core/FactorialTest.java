package com.teb.practice.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.teb.practice.exception.NegativeNumberException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class FactorialTest {

    private final Factorial factorial = new Factorial();

    @ParameterizedTest
    @CsvSource({"0, 1", "1, 1", "4, 24", "9, 362880", "16, 20922789888000"})
    void testFactorial(int input, long result) {

        assertEquals(result, factorial.findFactorial(input));
    }

    @Test
    void testThrowsExceptionForInvalidInput() {

        Exception e = assertThrows(RuntimeException.class, () -> factorial.findFactorial(-20));
        assertInstanceOf(NegativeNumberException.class, e);
    }
}
