package com.teb.practice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.util.List;

class PlusMinusTest {

    private final PlusMinus plusMinus = new PlusMinus();

    @Test
    void testRatio() {

        List<String> list = plusMinus.calculate(List.of(1, 6, -3, 0, 4, 5, -8, 2, 7));

        assertEquals(List.of("0.666667", "0.222222", "0.111111"), list);
    }
}
