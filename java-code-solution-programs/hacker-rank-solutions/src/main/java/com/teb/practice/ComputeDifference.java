package com.teb.practice;

/*
 * The Difference class has a private integer array (elements) for storing N non-negative integers,
 *  and a public integer (maximumDifference) for storing the maximum absolute difference.
 * Complete the Difference class by writing the following:
 * - A class constructor that takes an array of integers as a parameter and saves it to the elements instance variable.
 * - A computeDifference method that finds the maximum absolute difference between any 2 numbers in elements
 *    and stores it in the maximumDifference instance variable.
 */

import static java.util.Arrays.stream;
import static java.util.Collections.max;
import static java.util.Collections.min;

import java.util.List;

public class ComputeDifference {

    private final int[] elements;

    ComputeDifference(int[] elements) {
        this.elements = elements;
    }

    protected int compute() {

        List<Integer> inputList = stream(elements).boxed().toList();
        return max(inputList) - min(inputList);
    }
}
