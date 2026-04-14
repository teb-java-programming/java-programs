package com.teb.practice.core;

import static java.lang.Math.abs;

public class CommonFactor {

    protected int greatestCommonFactor(int inputOne, int inputTwo) {

        inputOne = abs(inputOne);
        inputTwo = abs(inputTwo);

        if (inputOne % inputTwo == 0) return inputTwo;
        if (inputTwo % inputOne == 0) return inputOne;

        while (inputTwo != 0) {
            int temp = inputOne % inputTwo;
            inputOne = inputTwo;
            inputTwo = temp;
        }

        return inputOne;
    }

    protected int lowestCommonMultiple(int inputOne, int inputTwo) {

        return (inputOne * inputTwo) / greatestCommonFactor(inputOne, inputTwo);
    }
}
