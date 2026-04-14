package com.teb.practice.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.teb.practice.exception.NegativeNumberException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PalindromeTest {

    private final Palindrome palindrome = new Palindrome();

    @ParameterizedTest
    @CsvSource({"level, true", "reviver, true", "hello, false"})
    void testPalindromeString(String input, boolean result) {

        assertEquals(result, palindrome.checkPalindromeString(input));
    }

    @ParameterizedTest
    @CsvSource({"1234, false", "5678, false", "67876, true"})
    void testPalindromeNumber(long input, boolean result) {

        assertEquals(result, palindrome.checkPalindromeNumber(input));
    }

    @Test
    void testThrowsExceptionForInvalidInput() {

        Exception e =
                assertThrows(RuntimeException.class, () -> palindrome.checkPalindromeNumber(-121));
        assertInstanceOf(NegativeNumberException.class, e);
    }
}
