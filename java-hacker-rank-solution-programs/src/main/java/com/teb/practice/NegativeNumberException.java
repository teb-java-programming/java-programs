package com.teb.practice;

/*
 * Write a Calculator class with a single method: int power(int,int).
 * The power method takes two integers, n and p, as parameters and returns the integer result of np.
 * If either n or p is negative, then the method must throw an exception with the message: n and p should be non-negative.
 */

import static com.teb.practice.constants.Constants.SCAN;

import static java.lang.Math.pow;
import static java.lang.System.out;

class Calculator {

    static int power(int n, int p) throws Exception {

        if (n < 0 || p < 0) {
            throw new Exception("n and p should be non-negative");
        }

        return (int) pow(n, p);
    }
}

public class NegativeNumberException {

    public static void main(String[] args) {

        out.print("Enter limit: ");
        int limit = SCAN.nextInt();

        out.println("Enter the values separated by single space:");
        while (limit-- > 0) {
            try {
                out.println(Calculator.power(SCAN.nextInt(), SCAN.nextInt()));
            } catch (Exception e) {
                out.println(e.getMessage());
            }
        }
    }
}
