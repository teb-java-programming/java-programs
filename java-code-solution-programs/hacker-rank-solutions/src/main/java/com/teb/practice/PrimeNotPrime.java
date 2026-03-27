package com.teb.practice;

/*
 * A prime is a natural number greater than 1 that has no positive divisors other than 1 and itself.
 * Given a number, n, determine and print whether it is Prime or Not prime.
 * */

import static java.lang.Math.sqrt;

public class PrimeNotPrime {

    protected boolean isPrime(int input) {

        if (input < 2) return false;

        for (int i = 2; i <= sqrt(input); i++) {
            if (input % i == 0) return false;
        }

        return true;
    }
}
