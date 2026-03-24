package com.teb.practice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class HumanReadableTimeTest {

    private final HumanReadableTime humanReadableTime = new HumanReadableTime();

    @Test
    void testReadableTime() {

        assertEquals("96:48:24", humanReadableTime.getReadableFormat(348504));
    }

    @Test
    void testThrowsExceptionForInvalidInput() {

        Exception e =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> humanReadableTime.getReadableFormat(1748108));
        assertEquals("The time input limit is 359999", e.getMessage());
    }
}
