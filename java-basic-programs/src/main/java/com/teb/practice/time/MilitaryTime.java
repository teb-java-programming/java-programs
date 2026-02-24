package com.teb.practice.time;

import static com.teb.practice.constants.Constants.SCAN;

import static java.lang.System.out;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class MilitaryTime {

    // 04:08:16pm
    private static final String AM_PM_FORMAT = "hh:mm:ssa";
    private static final String MILITARY_FORMAT = "HH:mm:ss";

    private static DateTimeFormatter inputFormat;
    private static DateTimeFormatter outputFormat;

    private static String getMilitaryTime(String time12Hour) {

        inputFormat = DateTimeFormatter.ofPattern(AM_PM_FORMAT);
        outputFormat = DateTimeFormatter.ofPattern(MILITARY_FORMAT);

        LocalTime time = LocalTime.parse(time12Hour, inputFormat);

        return time.format(outputFormat);
    }

    private static String get12HourTime(String timeMilitary) {

        inputFormat = DateTimeFormatter.ofPattern(MILITARY_FORMAT);
        outputFormat = DateTimeFormatter.ofPattern(AM_PM_FORMAT);

        LocalTime time = LocalTime.parse(timeMilitary, inputFormat);

        return time.format(outputFormat);
    }

    public static void main(String[] args) {

        out.print("Enter time in AM/PM format to convert to military format: ");
        String time12Hour = SCAN.next();
        out.print("Enter time in military format to convert to AM/PM format: ");
        String timeMilitary = SCAN.next();

        out.println("Military format: " + getMilitaryTime(time12Hour));
        out.println("AM/PM format: " + get12HourTime(timeMilitary));
    }
}
