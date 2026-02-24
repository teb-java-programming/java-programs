package com.teb.practice;

/*
 * Count the number of words in a sequence of words in CamelCase
 */

import static com.teb.practice.constants.Constants.SCAN;

import static java.lang.Character.isUpperCase;
import static java.lang.System.out;

public class CamelCase {

    private static int camelCase(String input) {

        int count = 1;

        for (int i = 0; i < input.length() - 1; i++) {
            if (isUpperCase(input.charAt(i + 1))) count++;
        }

        return count;
    }

    public static void main(String[] args) {

        out.print("Enter text in Camel Case: ");
        out.println("Number of words: " + camelCase(SCAN.next()));
    }
}
