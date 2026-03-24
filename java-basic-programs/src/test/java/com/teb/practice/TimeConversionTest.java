package com.teb.practice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TimeConversionTest {

    private static final String AM_PM_TIME = "04:08:16pm";
    private static final String MILITARY_TIME = "16:08:16";

    private final TimeConversion timeConversion = new TimeConversion();

    @Test
    void testMilitaryTime() {

        assertEquals(MILITARY_TIME, timeConversion.getMilitaryTime(AM_PM_TIME));
    }

    @Test
    void test12HourTime() {

        assertEquals(AM_PM_TIME, timeConversion.get12HourTime(MILITARY_TIME));
    }
}
