package com.teb.practice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class FindOutlierTest {

    private static final int[] ARRAY_ONE = {8, 2, -12, 32, 9, 16, 32};
    private static final int[] ARRAY_TWO = {3, 7, 13, -5, 11, -17, 8};
    private static final int[] ARRAY_THREE = {208, 121, 7, 17, 1901, 221, 7, 1, 351, 1, 71};

    private final FindOutlier findOutlier = new FindOutlier();

    static Stream<Arguments> outlierProvider() {

        return Stream.of(
                Arguments.of(ARRAY_ONE, 9),
                Arguments.of(ARRAY_TWO, 8),
                Arguments.of(ARRAY_THREE, 208),
                Arguments.of(new int[] {0}, 0),
                Arguments.of(new int[] {1}, 1));
    }

    @ParameterizedTest
    @MethodSource("outlierProvider")
    void testOutlier(int[] inputArray, int result) {

        assertEquals(result, findOutlier.findOutlier(inputArray));
        assertEquals(result, findOutlier.findOutlierUsingStream(inputArray));
    }
}
