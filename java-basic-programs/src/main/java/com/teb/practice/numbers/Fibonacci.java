package com.teb.practice.numbers;

import static com.teb.practice.constants.Constants.SCAN;
import static com.teb.practice.constants.Constants.SPACE;

import static java.lang.System.out;

public class Fibonacci {

    private static int fibonacci(int input) {

        int result = 0;
        int temp = 0;
        int next = 1;

        if (input == 0) return 0;
        if (input == 1) return 1;

        while (input-- >= 0) {
            next += result;
            result = temp;
            temp = next;
        }

        return result;
    }

    public static void main(String[] args) {

        out.print("Enter limit: ");
        int limit = SCAN.nextInt();

        out.println("Fibonacci sequence up to the given limit: ");
        for (int i = 0; i < limit; i++) {
            out.print(fibonacci(i) + SPACE);
        }
    }
}
