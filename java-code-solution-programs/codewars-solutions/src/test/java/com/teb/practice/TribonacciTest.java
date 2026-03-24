package com.teb.practice;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class TribonacciTest {

    private final Tribonacci tribonacci = new Tribonacci();

    static Stream<Arguments> tribonacciProvider() {

        return Stream.of(
                Arguments.of(new double[] {0.0, 1.0, 1.0}, 0, new double[] {}),
                Arguments.of(new double[] {0.0, 0.0, 1.0}, 1, new double[] {0.0}),
                Arguments.of(new double[] {0.0, 0.0, 0.0}, 4, new double[] {0.0, 0.0, 0.0, 0.0}),
                Arguments.of(
                        new double[] {0.0, 0.0, 1.0},
                        6,
                        new double[] {0.0, 0.0, 1.0, 1.0, 2.0, 4.0}),
                Arguments.of(
                        new double[] {0.0, 1.0, 1.0},
                        6,
                        new double[] {0.0, 1.0, 1.0, 2.0, 4.0, 7.0}),
                Arguments.of(
                        new double[] {1.0, 1.0, 1.0},
                        6,
                        new double[] {1.0, 1.0, 1.0, 3.0, 5.0, 9.0}),
                Arguments.of(
                        new double[] {4.0, 2.0, 1.0},
                        6,
                        new double[] {4.0, 2.0, 1.0, 7.0, 10.0, 18.0}));
    }

    @ParameterizedTest
    @MethodSource("tribonacciProvider")
    void testTribonacci(double[] start, int limit, double[] result) {

        assertArrayEquals(result, tribonacci.getTribonacci(start, limit));
    }
}
