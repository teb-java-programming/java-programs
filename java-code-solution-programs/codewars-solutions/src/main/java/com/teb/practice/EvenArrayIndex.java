package com.teb.practice;

/*
 * You are going to be given an array of integers.
 * Your job is to take that array and find an index N where the sum of the integers to the left of N
 * is equal to the sum of the integers to the right of N.
 */

import static java.util.stream.IntStream.of;

import java.util.Arrays;

public class EvenArrayIndex {

    protected int findEvenIndex(int[] inputArray) {

        int leftOfIndex = 0;
        int rightOfIndex = 0;

        for (int i = 0; i < inputArray.length; i++) {
            for (int j = i + 1; j < inputArray.length; j++) {
                rightOfIndex += inputArray[j];
            }
            if (leftOfIndex == rightOfIndex) return i;

            leftOfIndex += inputArray[i];
            rightOfIndex = 0;
        }

        return -1;
    }

    protected int findEvenIndexUsingStream(int[] inputArray) {

        for (int i = 0; i < inputArray.length; i++) {
            int rightOfIndexSum =
                    of(Arrays.copyOfRange(inputArray, i + 1, inputArray.length)).sum();
            int leftOfIndexSum = of(Arrays.copyOfRange(inputArray, 0, i)).sum();

            if (rightOfIndexSum == 0 && i == 0) return i;
            if (leftOfIndexSum == rightOfIndexSum) return i;
        }

        return -1;
    }
}
