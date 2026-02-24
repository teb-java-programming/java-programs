package com.teb.practice;

import static com.teb.practice.constants.Constants.SCAN;

import static java.lang.System.out;

public class HelloWorld {

    public static void main(String[] args) {

        out.print("Enter your name: ");
        String name = SCAN.nextLine();

        out.printf("Hello world! This is %s.", name);
    }
}
