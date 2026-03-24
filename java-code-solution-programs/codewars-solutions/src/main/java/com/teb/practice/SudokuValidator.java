package com.teb.practice;

/*
 * Write a function validSolution/ValidateSolution/valid_solution() that accepts a 2D array representing a Sudoku board,
 * and returns true if it is a valid solution, or false otherwise.
 * The cells of the Sudoku board may also contain 0's, which will represent empty cells.
 * Boards containing one or more zeroes are considered to be invalid solutions.
 */

import java.util.LinkedHashSet;
import java.util.Set;

public class SudokuValidator {

    private static final int LIMIT = 9;

    protected boolean isValidSolution(int[][] input) {

        Set<Integer> entry;

        isValidBoard(input);

        // Check unique rows
        for (int row = 0; row < LIMIT; row++) {
            entry = new LinkedHashSet<>();

            for (int column = 0; column < LIMIT; column++) {
                if (!entry.add(input[row][column])) {
                    throw new RuntimeException("Error! Duplicate row found");
                }
            }
        }

        // Check unique columns
        for (int row = 0; row < LIMIT; row++) {
            entry = new LinkedHashSet<>();

            for (int column = 0; column < LIMIT; column++) {
                if (!entry.add(input[column][row])) {
                    throw new RuntimeException("Error! Duplicate column found");
                }
            }
        }

        return true;
    }

    private void isValidBoard(int[][] input) {

        if (input.length < LIMIT || input.length > LIMIT)
            throw new RuntimeException("Error! Sudoku board has to be 9x9");

        // Check all values are within 1 and 9
        for (int row = 0; row < LIMIT; row++) {
            for (int column = 0; column < LIMIT; column++) {
                if (input[row][column] <= 0 || input[row][column] > 9) {
                    throw new RuntimeException("Error! Values can only be between 1 and 9");
                }
            }
        }
    }
}
