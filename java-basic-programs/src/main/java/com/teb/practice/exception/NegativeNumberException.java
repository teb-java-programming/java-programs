package com.teb.practice.exception;

public class NegativeNumberException extends RuntimeException {

    public NegativeNumberException() {
        super("Negative numbers are not allowed");
    }

    public NegativeNumberException(String message) {
        super(message);
    }
}
