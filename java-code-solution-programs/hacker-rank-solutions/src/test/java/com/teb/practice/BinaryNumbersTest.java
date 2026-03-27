package com.teb.practice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BinaryNumbersTest {

    private final BinaryNumbers binaryNumbers = new BinaryNumbers();

    @ParameterizedTest
    @CsvSource({"54716, 4", "467, 2", "1, 1", "0, 0"})
    void testBinaryNumbers(int input, long result) {

        assertEquals(result, binaryNumbers.calculateSumOfBinarySequence(input));
    }
}
