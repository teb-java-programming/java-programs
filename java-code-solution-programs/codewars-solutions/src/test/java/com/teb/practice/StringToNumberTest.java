package com.teb.practice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class StringToNumberTest {

    private final StringToNumber stringToNumber = new StringToNumber();

    @Test
    void testStringConvertedToNumber() {

        assertEquals(
                464592648,
                stringToNumber.convertToNumber(
                        "four hundred sixty four million five hundred ninety two thousand six hundred forty eight"));
    }

    @ParameterizedTest
    @CsvSource({"minus ten", "eight trillion", "this is invalid"})
    void testThrowsExceptionForInvalidString(String stringNumber) {

        assertThrows(
                IllegalArgumentException.class, () -> stringToNumber.convertToNumber(stringNumber));
    }
}
