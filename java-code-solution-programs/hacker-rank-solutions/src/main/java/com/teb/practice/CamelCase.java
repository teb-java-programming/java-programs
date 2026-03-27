package com.teb.practice;

/*
 * Count the number of words in a sequence of words in CamelCase
 */

import static java.lang.Character.isUpperCase;

public class CamelCase {

    protected int checkCamelCase(String input) {

        int count = 1;

        if (isUpperCase(input.charAt(0)))
            throw new RuntimeException("Input provided is not in Camel Case");

        for (int i = 1; i < input.length() - 1; i++) {
            if (isUpperCase(input.charAt(i + 1))) count++;
        }

        return count;
    }
}
