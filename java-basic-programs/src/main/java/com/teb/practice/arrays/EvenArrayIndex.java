package com.teb.practice.arrays;

import static java.lang.System.out;

import java.util.Arrays;
import java.util.stream.IntStream;

public class EvenArrayIndex {

    private static int getEvenIndex(int[] inputArray) {

        int leftOfIndex = 0;
        int rightOfIndex = 0;

        for (int i = 0; i < inputArray.length; i++) {
            for (int j = i + 1; j < inputArray.length; j++) {
                rightOfIndex += inputArray[j];
            }
            if (leftOfIndex == rightOfIndex) return i;

            leftOfIndex += inputArray[i];
            // Resetting sum of values on right
            rightOfIndex = 0;
        }

        return -1;
    }

    private static int getEvenIndexUsingStream(int[] inputArray) {

        for (int i = 0; i < inputArray.length; i++) {
            int rightOfIndexSum =
                    IntStream.of(Arrays.copyOfRange(inputArray, i + 1, inputArray.length)).sum();
            int leftOfIndexSum = IntStream.of(Arrays.copyOfRange(inputArray, 0, i)).sum();

            if (rightOfIndexSum == 0 && i == 0) return i;
            if (leftOfIndexSum == rightOfIndexSum) return i;
        }

        return -1;
    }

    public static void main(String[] args) {

        int[] arrayOne = {1, 100, 50, -51, 1, 1};
        int[] arrayTwo = {20, 10, -80, 100, 10, 15, 35};

        out.println("Result index: " + getEvenIndex(arrayOne));
        out.println("Result index (using Stream): " + getEvenIndexUsingStream(arrayTwo));
    }
}
