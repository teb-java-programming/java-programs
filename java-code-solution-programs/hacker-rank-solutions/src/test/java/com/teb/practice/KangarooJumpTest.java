package com.teb.practice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KangarooJumpTest {

    KangarooJump kangarooJump = new KangarooJump();

    @ParameterizedTest
    @CsvSource({
        "3, 8, 3, 8, YES",
        "1, 2, 5, 4, NO",
        "5, 2, 1, 4, YES",
        "7, 5, 2, 3, NO",
        "3, 4, 5, 1, NO"
    })
    void testJump(int positionOne, int rateOne, int positionTwo, int rateTwo, String result) {

        assertEquals(result, kangarooJump.syncJump(positionOne, rateOne, positionTwo, rateTwo));
    }
}
