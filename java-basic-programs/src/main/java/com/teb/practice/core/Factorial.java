package com.teb.practice.core;

import com.teb.practice.exception.NegativeNumberException;

public class Factorial {

    protected long findFactorial(int input) {

        if (input < 0) throw new NegativeNumberException();

        if (input <= 1) return 1;

        return input * findFactorial(--input);
    }
}
