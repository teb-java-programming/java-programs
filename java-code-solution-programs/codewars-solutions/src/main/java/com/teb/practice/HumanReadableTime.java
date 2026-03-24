package com.teb.practice;

/*
 * Write a function, which takes a non-negative integer (seconds) as input
 * and returns the time in a human-readable format (HH:MM:SS)
 *      HH = hours, padded to 2 digits, range: 00 - 99
 *      MM = minutes, padded to 2 digits, range: 00 - 59
 *      SS = seconds, padded to 2 digits, range: 00 - 59
 * The maximum time never exceeds 359999 (99:59:59)
 */

public class HumanReadableTime {

    protected String getReadableFormat(int seconds) {

        if (seconds > 359999) throw new IllegalArgumentException("The time input limit is 359999");

        return String.format("%02d:%02d:%02d", seconds / 3600, (seconds / 60) % 60, seconds % 60);
    }
}
