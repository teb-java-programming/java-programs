package com.teb.practice.numbers;

import static com.teb.practice.constants.Constants.SCAN;

import static java.lang.System.out;

public class Factorial {

    private static long factorial(int input) {

        long temp = input;
        long result = 1;

        if (input > 0) {
            temp *= factorial(--input);
            result *= temp;
        }

        return result;
    }

    public static void main(String[] args) {

        out.print("Enter input: ");
        int input = SCAN.nextInt();

        out.println("Factorial result: " + factorial(input));
    }
}
