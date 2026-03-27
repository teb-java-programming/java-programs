package com.teb.practice;

/*
 * Given five positive integers, find the minimum and maximum values that can be calculated by summing exactly four of the five integers.
 * Then print the respective minimum and maximum values as a single line of two space-separated long integers.
 */

import java.util.List;

public class MiniMaxSum {

    private long getSum(List<Integer> inputList, boolean flag) {

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

        return flag ? minimumSum : maximumSum;
    }

    protected long minimumSum(List<Integer> inputList) {

        return getSum(inputList, true);
    }

    protected long maximumSum(List<Integer> inputList) {

        return getSum(inputList, false);
    }
}
