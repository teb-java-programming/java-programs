package com.teb.practice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class NthOccurrenceTest {

    private static final String TARGET = "pneumonoultramicroscopicsilicovolcanoconiosis";

    private final NthOccurrence nthOccurrence = new NthOccurrence();

    static Stream<Arguments> searchProvider() {
        return Stream.of(
                Arguments.of("o", 4, 20),
                Arguments.of("i", 4, 27),
                Arguments.of("n", 4, 39),
                Arguments.of("s", 4, 44),
                Arguments.of("x", 4, 0));
    }

    @ParameterizedTest
    @MethodSource("searchProvider")
    void testFindsTheNthOccurrence(String search, int occur, int result) {

        assertEquals(result, nthOccurrence.getOccurrence(TARGET, search, occur));
    }
}
