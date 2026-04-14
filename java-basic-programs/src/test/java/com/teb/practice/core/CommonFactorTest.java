package com.teb.practice.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CommonFactorTest {

    private final CommonFactor commonFactor = new CommonFactor();

    @ParameterizedTest
    @CsvSource({"9, 16, 1", "24, 6, 6", "5, 40, 5", "-11, -19, 1"})
    void testGreatestCommonFactor(int inputOne, int inputTwo, int result) {

        assertEquals(result, commonFactor.greatestCommonFactor(inputOne, inputTwo));
    }

    @ParameterizedTest
    @CsvSource({"9, 16, 144", "24, 6, 24", "5, 31, 155", "-11, -18, 198"})
    void testLowestCommonMultiple(int inputOne, int inputTwo, int result) {

        assertEquals(result, commonFactor.lowestCommonMultiple(inputOne, inputTwo));
    }
}
