package com.teb.practice.numbers;

import static com.teb.practice.constants.Constants.SCAN;
import static com.teb.practice.numbers.GreatestCommonFactor.commonFactor;

import static java.lang.System.out;

public class LowestCommonMultiple {

    private static int commonMultiple(int inputOne, int inputTwo) {

        return (inputOne * inputTwo) / commonFactor(inputOne, inputTwo);
    }

    public static void main(String[] args) {

        out.print("Enter first number: ");
        int firstNumber = SCAN.nextInt();
        out.print("Enter second number: ");
        int secondNumber = SCAN.nextInt();

        out.println("The LCM is: " + commonMultiple(firstNumber, secondNumber));
    }
}
