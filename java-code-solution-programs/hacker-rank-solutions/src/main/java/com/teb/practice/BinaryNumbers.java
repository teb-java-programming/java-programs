package com.teb.practice;

/*
 * Given a base-10 integer, n, convert it to binary (base-2).
 * Then find and print the base-10 integer denoting the maximum number of consecutive 1's in n's binary representation.
 * When working with different bases, it is common to show the base as a subscript.
 */

import static com.teb.practice.constants.Constants.BLANK;

import static java.lang.Long.parseLong;
import static java.lang.Long.toBinaryString;
import static java.lang.Math.max;

import java.util.List;

public class BinaryNumbers {

    protected long calculateSumOfBinarySequence(int input) {

        List<String> stringList = List.of(toBinaryString(input).split(BLANK));
        long temp = 0;
        long sum = 0;

        for (String index : stringList) {
            long parsedLong = parseLong(index);

            if (parsedLong == 1) {
                sum += parsedLong;
            } else {
                if (sum != 0) temp = sum;
                sum = 0;
            }
        }

        return max(temp, sum);
    }
}
