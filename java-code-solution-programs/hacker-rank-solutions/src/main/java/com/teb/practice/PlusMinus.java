package com.teb.practice;

/*
 * Given an array of integers, calculate the ratios of its elements that are positive, negative, and zero.
 * Print the decimal value of each fraction on a new line with 6 places after the decimal.
 */

import static com.teb.practice.constants.Constants.SCAN;

import static java.lang.System.out;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class PlusMinus {

    private static void plusMinus(List<Integer> inputList) {

        int total = inputList.size();
        double positive = 0;
        double negative = 0;
        double zero = 0;

        for (Integer integer : inputList) {
            if (integer > 0) positive++;
            else if (integer < 0) negative++;
            else zero++;
        }

        DecimalFormat decimalFormat = new DecimalFormat("###.######");
        // Set this value to print zeros after the decimal point
        decimalFormat.setMinimumFractionDigits(6);

        out.println(decimalFormat.format(positive / total));
        out.println(decimalFormat.format(negative / total));
        out.println(decimalFormat.format(zero / total));
    }

    public static void main(String[] args) {

        List<Integer> inputList = new ArrayList<>();

        out.print("Enter the input count: ");
        int limit = SCAN.nextInt();

        while (limit-- > 0) {
            inputList.add(SCAN.nextInt());
        }

        plusMinus(inputList);
    }
}
