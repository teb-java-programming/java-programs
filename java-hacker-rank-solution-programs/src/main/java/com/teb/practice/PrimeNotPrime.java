package com.teb.practice;

/*
 * A prime is a natural number greater than 1 that has no positive divisors other than 1 and itself.
 * Given a number, n, determine and print whether it is Prime or Not prime.
 * */

import static com.teb.practice.constants.Constants.SCAN;

import static java.lang.Math.sqrt;
import static java.lang.System.out;

public class PrimeNotPrime {

    private static boolean isPrime(int input) {

        if (input < 2) return false;

        for (int i = 2; i <= sqrt(input); i++) {
            if (input % i == 0) return false;
        }

        return true;
    }

    public static void main(String[] args) {

        out.print("Enter the limit: ");
        int limit = SCAN.nextInt();

        out.println("Enter the values:");
        while (limit-- > 0) {
            if (isPrime(SCAN.nextInt())) out.println("Prime");
            else out.println("Not prime");
        }
    }
}
