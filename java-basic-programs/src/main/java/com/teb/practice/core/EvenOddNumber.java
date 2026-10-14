package com.teb.practice.core;

public class EvenOddNumber {

    protected String findEvenOdd(int input) {

        if (input == 0) throw new ArithmeticException("Zero is neither odd nor even");

        return input % 2 == 0 ? "Even" : "Odd";
    }
}
