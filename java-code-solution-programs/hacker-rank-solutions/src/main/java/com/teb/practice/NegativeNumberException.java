package com.teb.practice;

/*
 * Write a Calculator class with a single method: int power(int,int).
 * The power method takes two integers, n and p, as parameters and returns the integer result of np.
 * If either n or p is negative, then the method must throw an exception with the message: n and p should be non-negative.
 */

import static java.lang.Math.pow;

import java.util.InputMismatchException;

public class NegativeNumberException {

    protected int power(int n, int p) {

        if (n < 0 || p < 0) {
            throw new InputMismatchException("n and p should be non-negative");
        }

        return (int) pow(n, p);
    }
}
