package com.teb.practice;

/*
 * Reduce a string of lowercase characters in range ascii[‘a’..’z’]by doing a series of operations.
 * In each operation, select a pair of adjacent letters that match, and delete them.
 * Delete as many characters as possible using this method and return the resulting string.
 * If the final string is empty, return Empty String
 */

import static org.apache.commons.lang3.StringUtils.isEmpty;

public class SuperReducedString {

    protected String reducedString(String input) {

        for (int i = 1; i < input.length(); i++) {
            if (input.charAt(i) == input.charAt(i - 1)) {
                input = input.substring(0, i - 1) + input.substring(i + 1);
                i = 0;
            }
        }

        return isEmpty(input) ? "Empty String" : input;
    }
}
