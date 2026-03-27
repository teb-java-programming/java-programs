package com.teb.practice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class ComputeDifferenceTest {

    static Stream<Arguments> differenceProvider() {
        return Stream.of(
                Arguments.of(new int[] {6, 13, 22, 65}, 59),
                Arguments.of(new int[] {-41, 87, -54, 0}, 141));
    }

    @ParameterizedTest
    @MethodSource("differenceProvider")
    void testDifference(int[] inputArray, int result) {

        ComputeDifference computeDifference = new ComputeDifference(inputArray);

        assertEquals(result, computeDifference.compute());
    }
}
