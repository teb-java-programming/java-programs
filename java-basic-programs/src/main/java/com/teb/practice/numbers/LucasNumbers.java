package com.teb.practice.numbers;

import static com.teb.practice.constants.Constants.SCAN;

import static java.lang.System.out;

public class LucasNumbers {

    private static int lucasNumber(int input) {

        if (input == 0) return 2;
        if (input == 1) return 1;

        if (input > 0) {
            // Formula for positive input
            return lucasNumber(input - 1) + lucasNumber(input - 2);
        }
        // Formula for negative input
        return lucasNumber(input + 2) - lucasNumber(input + 1);
    }

    public static void main(String[] args) {

        out.print("Enter limit: ");
        int limit = SCAN.nextInt();

        out.println("Lucas number for the given limit: " + lucasNumber(limit));
    }
}
