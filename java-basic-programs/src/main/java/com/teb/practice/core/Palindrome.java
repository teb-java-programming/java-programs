package com.teb.practice.core;

import com.teb.practice.exception.NegativeNumberException;

public class Palindrome {

    protected boolean checkPalindromeString(String input) {

        return input.contentEquals(new StringBuilder(input).reverse());
    }

    protected boolean checkPalindromeNumber(long input) {

        if (input < 0) throw new NegativeNumberException();

        long temp = input;
        long result = 0;

        while (temp != 0) {
            result = result * 10 + (temp % 10);
            temp /= 10;
        }

        return input == result;
    }
}
