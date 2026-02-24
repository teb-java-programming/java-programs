package com.teb.practice;

/*
 * Given a base-10 integer, n, convert it to binary (base-2).
 * Then find and print the base-10 integer denoting the maximum number of consecutive 1's in n's binary representation.
 * When working with different bases, it is common to show the base as a subscript.
 */

import static com.teb.practice.constants.Constants.SCAN;

import static java.lang.Long.toBinaryString;
import static java.lang.Math.max;
import static java.lang.System.out;
import static java.util.Arrays.asList;

import java.util.List;

public class BinaryNumbers {

    private static List<String> getStringArray(int input) {

        return asList(toBinaryString(input).split(""));
    }

    private static long calculateSumOfBinarySequence(List<String> stringList) {

        long temp = 0;
        long sum = 0;

        for (String index : stringList) {
            long input = Long.parseLong(index);

            if (input != 0) {
                sum += input;
            } else {
                if (sum != 0) temp = sum;
                sum = 0;
            }
        }

        return max(temp, sum);
    }

    public static void main(String[] args) {

        out.print("Enter the base-10 number: ");
        int input = SCAN.nextInt();

        out.println(
                "Sum of highest sequence of 1's: "
                        + calculateSumOfBinarySequence(getStringArray(input)));
    }
}
