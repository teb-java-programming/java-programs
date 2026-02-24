package com.teb.practice.numbers;

import static com.teb.practice.constants.Constants.SCAN;

import static java.lang.System.out;

public class GreatestCommonFactor {

    protected static int commonFactor(int inputOne, int inputTwo) {

        int temp;

        if (inputOne % inputTwo == 0) return inputTwo;
        else if (inputTwo % inputOne == 0) return inputOne;
        else {
            while (inputOne >= 1 && inputTwo >= 1) {
                temp = inputOne % inputTwo;
                inputOne = inputTwo;
                inputTwo = temp;
            }
            return inputOne;
        }
    }

    public static void main(String[] args) {

        out.print("Enter first number: ");
        int firstNumber = SCAN.nextInt();
        out.print("Enter second number: ");
        int secondNumber = SCAN.nextInt();

        out.println("The GCF is: " + commonFactor(firstNumber, secondNumber));
    }
}
