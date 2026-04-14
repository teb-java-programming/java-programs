package com.teb.practice.core;

import static java.time.LocalTime.parse;
import static java.time.format.DateTimeFormatter.ofPattern;
import static java.util.Locale.UK;

public class TimeConversion {

    private static final String AM_PM_FORMAT = "hh:mm:ssa";
    private static final String MILITARY_FORMAT = "HH:mm:ss";

    protected String getMilitaryTime(String time12Hour) {

        return parse(time12Hour, ofPattern(AM_PM_FORMAT, UK)).format(ofPattern(MILITARY_FORMAT));
    }

    protected String get12HourTime(String timeMilitary) {

        return parse(timeMilitary, ofPattern(MILITARY_FORMAT)).format(ofPattern(AM_PM_FORMAT, UK));
    }
}
