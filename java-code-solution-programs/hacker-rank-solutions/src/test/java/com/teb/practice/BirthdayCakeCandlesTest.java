package com.teb.practice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import java.util.InputMismatchException;
import java.util.List;

class BirthdayCakeCandlesTest {

    private final BirthdayCakeCandles birthdayCakeCandles = new BirthdayCakeCandles();

    @Test
    void testBirthdayCandles() {

        List<Integer> candlesList = List.of(12, 9, 4, 12, 1, 5);

        assertEquals(2, birthdayCakeCandles.birthdayCandles(candlesList));
    }

    @Test
    void testThrowsExceptionForInvalidInput() {

        List<Integer> candlesList = List.of(0, -4);

        Exception e =
                assertThrows(
                        RuntimeException.class,
                        () -> birthdayCakeCandles.birthdayCandles(candlesList));
        assertInstanceOf(InputMismatchException.class, e);
    }
}
