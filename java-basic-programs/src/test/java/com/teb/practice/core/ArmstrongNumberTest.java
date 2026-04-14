package com.teb.practice.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.teb.practice.exception.NegativeNumberException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ArmstrongNumberTest {

    private final ArmstrongNumber armstrongNumber = new ArmstrongNumber();

    @ParameterizedTest
    @CsvSource({"6, true", "1234, false", "93084, true", "24678050, true"})
    void testPalindromeNumber(long input, boolean result) {

        assertEquals(result, armstrongNumber.checkArmstrong(input));
    }

    @Test
    void testThrowsExceptionForInvalidInput() {

        Exception e =
                assertThrows(RuntimeException.class, () -> armstrongNumber.checkArmstrong(-121));
        assertInstanceOf(NegativeNumberException.class, e);
    }
}
