package com.teb.practice;

/*
 * You need to create a fibonacci function that given a signature array/list,
 * returns the first n elements - signature included of the so seeded sequence.
 * Signature will always contain 3 numbers; n will always be a non-negative number;
 * if n == 0, then return an empty array and be ready for anything else which is not clearly specified
 */

import static java.lang.System.arraycopy;

public class Tribonacci {

    protected double[] getTribonacci(double[] start, int limit) {

        double[] result = new double[limit];
        double sum = 0;

        // Returns empty array when limit is zero
        if (limit == 0) return result;

        // Returns data entries from start array corresponding to limit
        if (limit < 3) {
            arraycopy(start, 0, result, 0, limit);

            return result;
        }

        // Initializing result array with start array
        for (int i = 0; i < start.length; i++) {
            sum += start[i];
            result[i] = start[i];
        }

        // Adding rest of the relevant data the result array
        for (int i = start.length; i < limit; i++) {
            result[i] = sum;
            sum += result[i - 1] + result[i - 2];
        }

        return result;
    }
}
