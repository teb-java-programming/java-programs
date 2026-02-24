package com.teb.practice.games;

import static java.lang.System.out;

import java.util.LinkedHashSet;
import java.util.Set;

public class SudokuValidator {

    static final int LIMIT = 9;

    private static boolean isValidSolution(int[][] input) {

        if (!isValidBoard(input)) return false;

        // Check unique rows
        for (int row = 0; row < LIMIT; row++) {
            Set<Integer> entry = new LinkedHashSet<>();
            for (int column = 0; column < LIMIT; column++) {
                if (!entry.add(input[row][column])) {
                    out.println("Duplicate value found.");
                    return false;
                }
            }
            out.println(entry);
            entry.clear();
        }

        // Check unique columns
        for (int row = 0; row < LIMIT; row++) {
            Set<Integer> entry = new LinkedHashSet<>();
            for (int column = 0; column < LIMIT; column++) {
                if (!entry.add(input[column][row])) {
                    out.println("Duplicate value found.");
                    return false;
                }
            }
            out.println(entry);
            entry.clear();
        }

        // Check unique blocks of three
        for (int row = 0; row < LIMIT - 2; row += 3) {
            Set<Integer> entry = new LinkedHashSet<>();
            for (int column = 0; column < LIMIT - 2; column += 3) {
                out.print("Block of three: ");
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (!entry.add(input[row + i][column + j])) {
                            out.println("Duplicate value found.");
                            return false;
                        }
                    }
                }
                out.println(entry);
                entry.clear();
            }
        }

        return true;
    }

    private static boolean isValidBoard(int[][] input) {

        if (input.length < LIMIT || input.length > LIMIT) return false;

        // Check all values to be within 1 and 9
        for (int row = 0; row < LIMIT; row++) {
            for (int column = 0; column < LIMIT; column++) {
                if (input[row][column] <= 0 || input[row][column] > 9) {
                    out.println("Values can only be between 1 and 9.");
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {

        int[][] input = {
            {5, 3, 4, 6, 7, 8, 9, 1, 2},
            {6, 7, 2, 1, 9, 5, 3, 4, 8},
            {1, 9, 8, 3, 4, 2, 5, 6, 7},
            {8, 5, 9, 7, 6, 1, 4, 2, 3},
            {4, 2, 6, 8, 5, 3, 7, 9, 1},
            {7, 1, 3, 9, 2, 4, 8, 5, 6},
            {9, 6, 1, 5, 3, 7, 2, 8, 4},
            {2, 8, 7, 4, 1, 9, 6, 3, 5},
            {3, 4, 5, 2, 8, 6, 1, 7, 9}
        };

        if (isValidSolution(input)) out.println("Sudoku solution is valid.");
    }
}
