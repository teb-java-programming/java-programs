package com.teb.practice;

/*
 * Given an array of integers, calculate the ratios of its elements that are positive, negative, and zero.
 * Print the decimal value of each fraction on a new line with 6 places after the decimal.
 */

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class PlusMinus {

    protected List<String> calculate(List<Integer> inputList) {

        List<String> resultList = new ArrayList<>();
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

        resultList.add(decimalFormat.format(positive / total));
        resultList.add(decimalFormat.format(negative / total));
        resultList.add(decimalFormat.format(zero / total));

        return resultList;
    }
}
