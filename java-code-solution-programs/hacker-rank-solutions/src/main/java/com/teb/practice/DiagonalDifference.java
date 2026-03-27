package com.teb.practice;

/*
 * Given a square matrix, calculate the absolute difference between the sums of its diagonals.
 */

import static java.lang.Math.abs;

import java.util.List;

public class DiagonalDifference {

    protected int difference(List<List<Integer>> matrix) {

        int limit = matrix.size();
        int leftToRight = 0;
        int rightToLeft = 0;

        for (int i = 0; i < limit; i++) {
            leftToRight += matrix.get(i).get(i);
            rightToLeft += matrix.get(i).get(limit - i - 1);
        }

        return abs(leftToRight - rightToLeft);
    }
}
