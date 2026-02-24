package com.teb.practice.numbers;

import static com.teb.practice.constants.Constants.SCAN;

import static java.lang.System.out;

public class EvenNumberPlay {

    private static int evenAdd(int start, int limit) {

        int result = start;

        while (start < limit) {
            start += 2;
            result += start;
        }

        return result;
    }

    private static int evenMultiply(int start, int limit) {

        int result = start;

        while (start < limit) {
            start += 2;
            result *= start;
        }

        return result;
    }

    public static void main(String[] args) {

        out.print("Enter the first number: ");
        int start = SCAN.nextInt();
        out.print("Enter the last number: ");
        int limit = SCAN.nextInt();

        // If the starting point is odd, move to next even number
        if (start % 2 != 0) ++start;
        // If the limit is odd, move to previous even number
        if (limit % 2 != 0) --limit;

        out.println("Select your operation");
        out.println("1. Add");
        out.println("2. Multiply");
        out.print("Your choice: ");
        int choice = SCAN.nextInt();

        switch (choice) {
            case 1:
                out.println(evenAdd(start, limit));
                break;
            case 2:
                out.println(evenMultiply(start, limit));
                break;
            default:
                out.println("Invalid option!");
        }
    }
}
