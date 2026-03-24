package com.teb.practice;

/*
 * The Difference class has a private integer array (elements) for storing N non-negative integers,
 *  and a public integer (maximumDifference) for storing the maximum absolute difference.
 * Complete the Difference class by writing the following:
 * - A class constructor that takes an array of integers as a parameter and saves it to the elements instance variable.
 * - A computeDifference method that finds the maximum absolute difference between any 2 numbers in elements
 *    and stores it in the maximumDifference instance variable.
 */

import static com.teb.practice.constants.Constants.SCAN;

import static java.lang.System.out;
import static java.util.Arrays.stream;
import static java.util.Collections.max;
import static java.util.Collections.min;

import java.util.List;

class Difference {

    private final int[] elements;
    public int maximumDifference;

    Difference(int[] elements) {
        this.elements = elements;
    }

    void computeDifference() {

        List<Integer> inputList = stream(elements).boxed().toList();
        maximumDifference = max(inputList) - min(inputList);
    }
}

public class ComputeDifference {

    public static void main(String[] args) {

        out.print("Enter array limit: ");
        int limit = SCAN.nextInt();
        int[] inputArray = new int[limit];

        out.println("Enter the " + limit + " values:");
        for (int i = 0; i < limit; i++) {
            inputArray[i] = SCAN.nextInt();
        }

        Difference difference = new Difference(inputArray);
        difference.computeDifference();

        out.println("Maximum difference: " + difference.maximumDifference);
    }
}
