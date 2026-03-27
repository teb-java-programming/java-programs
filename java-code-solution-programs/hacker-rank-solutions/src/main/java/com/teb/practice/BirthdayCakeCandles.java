package com.teb.practice;

/*
 * You are in charge of the cake for a child's birthday.
 * It will have one candle for each year of their total age.
 * They will only be able to blow out the tallest of the candles.
 * Your task is to count how many candles are the tallest.
 */

import static java.util.Collections.max;

import java.util.InputMismatchException;
import java.util.List;

public class BirthdayCakeCandles {

    protected int birthdayCandles(List<Integer> candlesList) {

        int tallest = max(candlesList);
        int candleCount = 0;

        for (int candle : candlesList) {
            if (candle < 1)
                throw new InputMismatchException("Candle height cannot be zero or negative");
            if (candle == tallest) candleCount++;
        }

        return candleCount;
    }
}
