package com.teb.practice;

/*
 * Digital root is the recursive sum of all the digits in a number.
 * Given n, take the sum of the digits of n.
 * If that value has more than one digit, continue reducing in this way until a single-digit number is produced.
 * The input will be a non-negative integer.
 */

public class DigitalRoot {

    protected int digitalRoot(int input) {

        if (input != 0 && input % 9 == 0) return 9;
        else return input % 9;
    }
}
