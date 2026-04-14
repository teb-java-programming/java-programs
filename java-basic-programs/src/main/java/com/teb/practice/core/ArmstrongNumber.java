package com.teb.practice.core;

import static java.lang.Math.pow;
import static java.lang.String.valueOf;

import com.teb.practice.exception.NegativeNumberException;

public class ArmstrongNumber {

    protected boolean checkArmstrong(long input) {

        if (input < 0) throw new NegativeNumberException();

        if (input < 10) return true;

        long temp = input;
        int length = valueOf(input).length();
        long sum = 0;

        while (temp != 0) {
            sum += (long) pow(temp % 10, length);
            temp /= 10;
        }

        return input == sum;
    }
}
