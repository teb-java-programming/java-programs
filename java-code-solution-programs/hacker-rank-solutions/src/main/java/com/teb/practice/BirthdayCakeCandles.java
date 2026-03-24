package com.teb.practice;

/*
 * You are in charge of the cake for a child's birthday.
 * It will have one candle for each year of their total age.
 * They will only be able to blow out the tallest of the candles.
 * Your task is to count how many candles are the tallest.
 */

import static com.teb.practice.constants.Constants.SCAN;

import static java.lang.System.out;
import static java.util.Collections.max;

import java.util.ArrayList;
import java.util.List;

public class BirthdayCakeCandles {

    private static int birthdayCakeCandles(List<Integer> candlesList) {

        int tallest = max(candlesList);
        int candleCount = 0;

        for (int candleLength : candlesList) {
            if (candleLength == tallest) candleCount++;
        }

        return candleCount;
    }

    public static void main(String[] args) {

        List<Integer> inputList = new ArrayList<>();

        out.print("Enter the number of candles: ");
        int candleCount = SCAN.nextInt();

        while (candleCount-- > 0) {
            inputList.add(SCAN.nextInt());
        }

        out.println("Count of tallest candles: " + birthdayCakeCandles(inputList));
    }
}
