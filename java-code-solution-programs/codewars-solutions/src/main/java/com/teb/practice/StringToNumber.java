package com.teb.practice;

/*
 * We want to convert a string into an integer.
 * The strings simply represent the numbers in words.
 */

import static java.util.Map.entry;
import static java.util.Map.ofEntries;

import java.util.Map;

public class StringToNumber {

    private static final Map<String, Integer> unitsMap;
    private static final Map<String, Integer> tensMap;

    static {
        unitsMap =
                ofEntries(
                        entry("zero", 0),
                        entry("one", 1),
                        entry("two", 2),
                        entry("three", 3),
                        entry("four", 4),
                        entry("five", 5),
                        entry("six", 6),
                        entry("seven", 7),
                        entry("eight", 8),
                        entry("nine", 9),
                        entry("ten", 10),
                        entry("eleven", 11),
                        entry("twelve", 12),
                        entry("thirteen", 13),
                        entry("fourteen", 14),
                        entry("fifteen", 15),
                        entry("sixteen", 16),
                        entry("seventeen", 17),
                        entry("eighteen", 18),
                        entry("nineteen", 19));

        tensMap =
                ofEntries(
                        entry("twenty", 20),
                        entry("thirty", 30),
                        entry("forty", 40),
                        entry("fifty", 50),
                        entry("sixty", 60),
                        entry("seventy", 70),
                        entry("eighty", 80),
                        entry("ninety", 90));
    }

    protected int convertToNumber(String input) {

        int next = 0;
        int result = 0;

        input = input.toLowerCase().trim();
        String[] inputArray = input.split("[ -]+");

        for (String word : inputArray) {
            if (unitsMap.containsKey(word)) {
                next += unitsMap.get(word);
            } else if (tensMap.containsKey(word)) {
                next += tensMap.get(word);
            } else if ("hundred".equals(word)) {
                next *= 100;
            } else if ("thousand".equals(word)) {
                next *= 1000;
                result += next;
                next = 0;
            } else if ("million".equals(word)) {
                next *= 1000000;
                result += next;
                next = 0;
            } else {
                throw new IllegalArgumentException("Invalid input provided: " + input);
            }
        }

        return result + next;
    }
}
