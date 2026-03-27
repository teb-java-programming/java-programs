package com.teb.practice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

class MiniMaxSumTest {

    private final MiniMaxSum miniMaxSum = new MiniMaxSum();

    static Stream<Arguments> sumNumberProvider() {
        return Stream.of(
                Arguments.of(List.of(8, 2, 4, 9, 5), 19, 26),
                Arguments.of(List.of(281, -514, 0, -404, 183), -735, 60));
    }

    @ParameterizedTest
    @MethodSource("sumNumberProvider")
    void testMinimumAndMaximumSum(List<Integer> inputList, long minimumSum, long maximumSum) {

        assertEquals(minimumSum, miniMaxSum.minimumSum(inputList));
        assertEquals(maximumSum, miniMaxSum.maximumSum(inputList));
    }
}
