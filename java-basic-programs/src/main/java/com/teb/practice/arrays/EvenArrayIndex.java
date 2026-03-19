package com.teb.practice.arrays;

import java.util.Arrays;
import java.util.stream.IntStream;

public class EvenArrayIndex {

    protected int getEvenIndex(int[] inputArray) {

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

    protected int getEvenIndexUsingStream(int[] inputArray) {

        for (int i = 0; i < inputArray.length; i++) {
            int rightOfIndexSum =
                    IntStream.of(Arrays.copyOfRange(inputArray, i + 1, inputArray.length)).sum();
            int leftOfIndexSum = IntStream.of(Arrays.copyOfRange(inputArray, 0, i)).sum();

            if (rightOfIndexSum == 0 && i == 0) return i;
            if (leftOfIndexSum == rightOfIndexSum) return i;
        }

        return -1;
    }

    protected int getEvenIndexOptimized(int[] inputArray) {

        int totalIndexSum = IntStream.of(inputArray).sum();
        int leftOfIndexSum = 0;

        for (int i = 0; i < inputArray.length; i++) {
            int rightOfIndexSum = totalIndexSum - leftOfIndexSum - inputArray[i];
            if (leftOfIndexSum == rightOfIndexSum) return i;
            leftOfIndexSum += inputArray[i];
        }

        return -1;
    }
}
