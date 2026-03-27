package com.teb.practice;

/*
 * You are choreographing a circus show with various animals.
 * For one act, you are given two kangaroos on a number line ready to jump in the positive direction (i.e, toward positive infinity).
 * The first kangaroo starts at location POSITION_ONE and moves at a rate of RATE_ONE meters per jump.
 * The second kangaroo starts at location POSITION_TWO and moves at a rate of RATE_TWO meters per jump.
 * You have to figure out a way to get both kangaroos at the same location at the same time as part of the show.
 * If it is possible, return YES, otherwise return NO.
 */

public class KangarooJump {

    private static final String YES = "YES";
    private static final String NO = "NO";

    protected String syncJump(int positionOne, int rateOne, int positionTwo, int rateTwo) {

        if ((rateOne <= rateTwo && positionOne < positionTwo)
                || (rateTwo <= rateOne && positionTwo < positionOne)) {
            return NO;
        }

        if (rateOne == rateTwo) {
            return YES;
        }

        return ((positionTwo - positionOne) % (rateOne - rateTwo) == 0) ? YES : NO;
    }
}
