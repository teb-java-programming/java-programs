package com.teb.practice.core;

import java.util.List;

public class SwapNumbers {

    protected List<Integer> swapWithTemp(int inputOne, int inputTwo) {

        int temp = inputOne;
        inputOne = inputTwo;
        inputTwo = temp;

        return List.of(inputOne, inputTwo);
    }

    protected List<Integer> swapWithoutTemp(int inputOne, int inputTwo) {

        inputOne += inputTwo;
        inputTwo = inputOne - inputTwo;
        inputOne -= inputTwo;

        return List.of(inputOne, inputTwo);
    }

    protected List<Integer> swapWithXOR(int inputOne, int inputTwo) {

        inputOne = inputOne ^ inputTwo;
        inputTwo = inputOne ^ inputTwo;
        inputOne = inputOne ^ inputTwo;

        return List.of(inputOne, inputTwo);
    }
}
