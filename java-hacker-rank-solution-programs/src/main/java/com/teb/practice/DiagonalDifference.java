package com.teb.practice;

/*
 * Given a square matrix, calculate the absolute difference between the sums of its diagonals.
 */

import static java.lang.Math.abs;
import static java.lang.System.out;

import java.util.List;

public class DiagonalDifference {

    private static int diagonalDifference(List<List<Integer>> matrix) {

        int limit = matrix.size();
        int leftToRight = 0;
        int rightToLeft = 0;

        for (int i = 0; i < limit; i++) {
            leftToRight += matrix.get(i).get(i);
            rightToLeft += matrix.get(i).get(limit - i - 1);
        }

        return abs(leftToRight - rightToLeft);
    }

    public static void main(String[] args) {

        List<List<Integer>> numberList =
                List.of(
                        List.of(7, 2, -5, 1, 9),
                        List.of(4, 1, 6, -3, 6),
                        List.of(9, 8, -4, -7, 5),
                        List.of(3, 10, 2, -5, 12),
                        List.of(12, 8, 16, 1, -15));

        out.println(diagonalDifference(numberList));
    }
}
