package com.teb.practice.numbers;

import static com.teb.practice.constants.Constants.SCAN;

import static java.lang.System.out;

public class DigitalRoot {

    private static int getDigitalRoot(int input) {

        if (input != 0 && input % 9 == 0) return 9;
        else return input % 9;
    }

    public static void main(String[] args) {

        out.print("Enter number: ");
        int input = SCAN.nextInt();

        out.println("Result: " + getDigitalRoot(input));
    }
}
