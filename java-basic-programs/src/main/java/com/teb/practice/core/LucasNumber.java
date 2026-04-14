package com.teb.practice.core;

import static java.lang.Math.abs;

public class LucasNumber {

    protected int findLucasNumber(int input) {

        if (input == 0) return 2;
        if (input == 1) return 1;

        int tempOne = 2;
        int tempTwo = 1;
        int absolute = abs(input);

        for (int i = 2; i <= absolute; i++) {
            int next = tempOne + tempTwo;
            tempOne = tempTwo;
            tempTwo = next;
        }

        return (input < 0 && (absolute & 1) == 1) ? -tempTwo : tempTwo;
    }
}
