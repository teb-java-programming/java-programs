package com.teb.practice;

/*
 * Given five positive integers, find the minimum and maximum values that can be calculated by summing exactly four of the five integers.
 * Then print the respective minimum and maximum values as a single line of two space-separated long integers.
 */

import static com.teb.practice.constants.Constants.SCAN;

import static java.lang.System.out;

import java.util.ArrayList;
import java.util.List;

public class MiniMaxSum {

    private static void miniMaxSum(List<Integer> inputList) {

        int size = inputList.size();
        long minimumSum = 0;
        long maximumSum = 0;

        inputList = inputList.stream().sorted().toList();

        for (int i = 0; i < size - 1; i++) {
            minimumSum += inputList.get(i);
        }
        for (int i = 1; i < size; i++) {
            maximumSum += inputList.get(i);
        }

        out.println(minimumSum + " " + maximumSum);
    }

    public static void main(String[] args) {

        List<Integer> inputList = new ArrayList<>();

        out.println("Enter the five numbers:");
        for (int i = 0; i < 5; i++) {
            inputList.add(SCAN.nextInt());
        }

        miniMaxSum(inputList);
    }
}
