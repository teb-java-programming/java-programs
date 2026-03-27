package com.teb.practice;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PrimeNotPrimeTest {

    private final PrimeNotPrime primeNotPrime = new PrimeNotPrime();

    @ParameterizedTest
    @CsvSource({"2", "83", "131", "167", "239"})
    void testIsPrime(int input) {

        assertTrue(primeNotPrime.isPrime(input));
    }

    @ParameterizedTest
    @CsvSource({"0", "36", "85", "-239", "333", "400"})
    void testIsNotPrime(int input) {

        assertFalse(primeNotPrime.isPrime(input));
    }
}
