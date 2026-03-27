package com.teb.practice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.util.List;

class DiagonalDifferenceTest {

    private static final List<List<Integer>> NUMBER_LIST =
            List.of(
                    List.of(7, 2, -5, 1, 9),
                    List.of(4, 1, 6, -3, 6),
                    List.of(9, 8, -4, -7, 5),
                    List.of(3, 10, 2, -5, 12),
                    List.of(12, 8, 16, 1, -15));

    private final DiagonalDifference diagonalDifference = new DiagonalDifference();

    @Test
    void testDiagonalDifference() {

        assertEquals(40, diagonalDifference.difference(NUMBER_LIST));
    }
}
