package com.teb.practice.numbers;

import static com.teb.practice.constants.Constants.SCAN;
import static com.teb.practice.constants.Constants.SPACE;

import static java.lang.System.arraycopy;
import static java.lang.System.out;

public class Tribonacci {

    private static int[] getTribonacciSeries(int[] start, int limit) {

        int[] result = new int[limit];
        int sum = 0;

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

    public static void main(String[] args) {

        out.println("Select your starting sequence");
        out.println("1. 0, 0, 1");
        out.println("2. 0, 1, 1");
        out.println("3. 1, 1, 1");
        out.print("Your choice: ");
        int choice = SCAN.nextInt();

        out.print("Enter the limit: ");
        int limit = SCAN.nextInt();
        int[] result;

        switch (choice) {
            case 1:
                out.print("Result series: ");
                result = getTribonacciSeries(new int[] {0, 0, 1}, limit);
                for (int values : result) {
                    out.print(values + SPACE);
                }
                break;
            case 2:
                out.print("Result series: ");
                result = getTribonacciSeries(new int[] {0, 1, 1}, limit);
                for (int values : result) {
                    out.print(values + SPACE);
                }
                break;
            case 3:
                out.print("Result series: ");
                result = getTribonacciSeries(new int[] {1, 1, 1}, limit);
                for (int values : result) {
                    out.print(values + SPACE);
                }
                break;
            default:
                out.println("Invalid option!");
        }
    }
}
