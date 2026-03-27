package com.teb.practice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class SuperReducedStringTest {

    private final SuperReducedString superReducedString = new SuperReducedString();

    @ParameterizedTest
    @CsvSource({
        "Mississippi, M",
        "bookkeeper, bper",
        "committee, coi",
        "breeziness, brzine",
        "aardvark, rdvark",
        "TT, Empty String"
    })
    void testReducedString(String input, String result) {

        assertEquals(result, superReducedString.reducedString(input));
    }
}
