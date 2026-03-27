package com.teb.practice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

class CircularArrayRotationTest {

    private final CircularArrayRotation circularArrayRotation = new CircularArrayRotation();

    static Stream<Arguments> arrayProvider() {

        return Stream.of(
                Arguments.of(
                        List.of(1, 2, 3, 4, 5, 6, 7, 8),
                        List.of(2, 3, 4, 5),
                        4,
                        List.of(7, 8, 1, 2)),
                Arguments.of(List.of(1, 2, 3, 4), List.of(0, 2), 10, List.of(3, 1)),
                Arguments.of(List.of(1), List.of(0), 1, List.of(1)),
                Arguments.of(List.of(-1, 2, 3, -4), List.of(1, 2, 3), 6, List.of(-4, -1, 2)));
    }

    @ParameterizedTest
    @MethodSource("arrayProvider")
    void testCircularArrayRotation(
            List<Integer> inputList,
            List<Integer> queriedList,
            int rotations,
            List<Integer> resultList) {

        assertEquals(
                resultList, circularArrayRotation.rotateArray(inputList, queriedList, rotations));
    }

    @Test
    void testInvalidInputThrowsException() {

        Exception e =
                assertThrows(
                        RuntimeException.class,
                        () ->
                                circularArrayRotation.rotateArray(
                                        List.of(1, 2, 3, 4), List.of(3, 4), 2));
        assertInstanceOf(IndexOutOfBoundsException.class, e);
    }
}
