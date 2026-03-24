package com.teb.practice;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeConversion {

    private static final String AM_PM_FORMAT = "hh:mm:ssa";
    private static final String MILITARY_FORMAT = "HH:mm:ss";

    private static DateTimeFormatter inputFormat;
    private static DateTimeFormatter outputFormat;

    protected String getMilitaryTime(String time12Hour) {

        inputFormat = DateTimeFormatter.ofPattern(AM_PM_FORMAT);
        outputFormat = DateTimeFormatter.ofPattern(MILITARY_FORMAT);

        LocalTime time = LocalTime.parse(time12Hour, inputFormat);

        return time.format(outputFormat);
    }

    protected String get12HourTime(String timeMilitary) {

        inputFormat = DateTimeFormatter.ofPattern(MILITARY_FORMAT);
        outputFormat = DateTimeFormatter.ofPattern(AM_PM_FORMAT);

        LocalTime time = LocalTime.parse(timeMilitary, inputFormat);

        return time.format(outputFormat);
    }
}
