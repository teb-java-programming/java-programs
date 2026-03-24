package com.teb.practice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class EvenArrayIndexTest {

    private final EvenArrayIndex evenArrayIndex = new EvenArrayIndex();

    static Stream<Arguments> evenIndexArrays() {

        return Stream.of(
                Arguments.of(new int[] {1, 100, 50, -51, 1, 1}, 1),
                Arguments.of(new int[] {20, 10, -80, 100, 10, 15, 35}, 4),
                Arguments.of(new int[] {0}, 0),
                Arguments.of(new int[] {1, -1, 0}, 2),
                Arguments.of(new int[] {}, -1),
                Arguments.of(new int[] {0, 1, -1}, 0),
                Arguments.of(new int[] {1, 2, 3, 6}, -1));
    }

    @ParameterizedTest
    @MethodSource("evenIndexArrays")
    void testEvenIndex(int[] inputArray, int resultIndex) {

        assertEquals(resultIndex, evenArrayIndex.findEvenIndex(inputArray));
        assertEquals(resultIndex, evenArrayIndex.findEvenIndexUsingStream(inputArray));
    }
}
