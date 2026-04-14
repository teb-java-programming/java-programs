package com.teb.practice.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class SwapNumbersTest {

    private final SwapNumbers swapNumbers = new SwapNumbers();

    private List<Integer> resultList = new ArrayList<>();

    @Test
    void testSwapWithTemp() {

        resultList = swapNumbers.swapWithTemp(14, 81);

        assertEquals(81, resultList.getFirst());
        assertEquals(14, resultList.getLast());
    }

    @Test
    void testSwapWithoutTemp() {

        resultList = swapNumbers.swapWithoutTemp(32, -1162);

        assertEquals(-1162, resultList.getFirst());
        assertEquals(32, resultList.getLast());
    }

    @Test
    void testSwapWithXOR() {

        resultList = swapNumbers.swapWithXOR(181318, 801952);

        assertEquals(801952, resultList.getFirst());
        assertEquals(181318, resultList.getLast());
    }
}
