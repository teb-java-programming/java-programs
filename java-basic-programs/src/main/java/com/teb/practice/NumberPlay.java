package com.teb.practice;

import static java.lang.Math.abs;

public class NumberPlay {

    // Even number operations
    protected int evenNumberAction(int start, int limit, String action) {

        int result = start;

        while (start < limit) {
            start += 2;
            switch (action) {
                case "+" -> result += start;
                case "*" -> result *= start;
                default -> throw new RuntimeException("Invalid option");
            }
        }

        return result;
    }

    // Factorial
    protected long factorial(int input) {

        long temp = input;
        long result = 1;

        if (input > 0) {
            temp *= factorial(--input);
            result *= temp;
        }

        return result;
    }

    // Fibonacci
    protected int fibonacci(int limit) {

        int result = 0;
        int temp = 0;
        int next = 1;

        if (limit == 0) return 0;
        if (limit == 1) return 1;

        while (limit-- >= 0) {
            next += result;
            result = temp;
            temp = next;
        }

        return result;
    }

    // Lucas number
    protected int lucasNumber(int input) {

        int tempOne = 2;
        int tempTwo = 1;
        int absolute = abs(input);

        if (input == 0) return 2;
        if (input == 1) return 1;

        for (int i = 2; i <= absolute; i++) {
            int next = tempOne + tempTwo;
            tempOne = tempTwo;
            tempTwo = next;
        }

        return (input < 0 && (absolute & 1) == 1) ? -tempTwo : tempTwo;
    }

    // GCF
    protected int greatestCommonFactor(int inputOne, int inputTwo) {

        inputOne = abs(inputOne);
        inputTwo = abs(inputTwo);

        if (inputOne % inputTwo == 0) return inputTwo;
        else if (inputTwo % inputOne == 0) return inputOne;
        else {
            while (inputTwo != 0) {
                int temp = inputOne % inputTwo;
                inputOne = inputTwo;
                inputTwo = temp;
            }

            return inputOne;
        }
    }

    // LCM
    protected int lowestCommonMultiple(int inputOne, int inputTwo) {

        return (inputOne * inputTwo) / greatestCommonFactor(inputOne, inputTwo);
    }
}
