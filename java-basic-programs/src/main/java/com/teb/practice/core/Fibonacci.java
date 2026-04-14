package com.teb.practice.core;

import com.teb.practice.exception.NegativeNumberException;

public class Fibonacci {

    protected int findFibonacci(int limit) {

        if (limit < 0) throw new NegativeNumberException("Negative limit is not allowed");

        if (limit == 0) return 0;
        if (limit == 1) return 1;

        int result = 0;
        int temp = 0;
        int next = 1;

        while (limit-- >= 0) {
            next += result;
            result = temp;
            temp = next;
        }

        return result;
    }
}
