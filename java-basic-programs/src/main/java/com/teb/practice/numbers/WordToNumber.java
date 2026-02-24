package com.teb.practice.numbers;

import static com.teb.practice.constants.Constants.SCAN;
import static com.teb.practice.constants.Constants.TENS_MAP;
import static com.teb.practice.constants.Constants.UNITS_MAP;

import static java.lang.System.out;

public class WordToNumber {

    private static int convertToNumber(String input) {

        int next = 0;
        int result = 0;

        input = input.toLowerCase().trim();
        String[] inputArray = input.split("[ -]+");

        for (String word : inputArray) {
            if (UNITS_MAP.containsKey(word)) {
                next += UNITS_MAP.get(word);
            } else if (TENS_MAP.containsKey(word)) {
                next += TENS_MAP.get(word);
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

    public static void main(String[] args) {

        out.print("Enter the number in words: ");
        String input = SCAN.nextLine();

        out.println("The numeral value for the input: " + convertToNumber(input));
    }
}
