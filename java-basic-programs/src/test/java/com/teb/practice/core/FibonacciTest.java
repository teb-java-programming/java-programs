package com.teb.practice.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.teb.practice.exception.NegativeNumberException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class FibonacciTest {

    private final Fibonacci fibonacci = new Fibonacci();

    @ParameterizedTest
    @CsvSource({"0, 0", "1, 1", "12, 144", "78, 375819880"})
    void testFibonacci(int limit, int result) {

        assertEquals(result, fibonacci.findFibonacci(limit));
    }

    @Test
    void testThrowsExceptionForInvalidInput() {

        Exception e = assertThrows(RuntimeException.class, () -> fibonacci.findFibonacci(-10));
        assertInstanceOf(NegativeNumberException.class, e);
    }
}
