package com.teb.practice;

/*
* Read a string, S, and print its integer value; if S cannot be converted to an integer, print Bad String.
*/

import static com.teb.practice.constants.Constants.SCAN;

import static java.lang.Integer.valueOf;
import static java.lang.System.out;

public class StringToIntegerException {

    public static void main(String[] args){

        out.print("Enter the string: ");

        try {
            out.println("Result: " + valueOf(SCAN.next()));
        } catch (Exception e) {
            out.println("Bad String");
        }
    }
}
