package com.teb.practice;

/*
 * You are given an array (which will have a length of at least 3, but could be very large) containing integers.
 * The array is either entirely comprised of odd integers or entirely comprised of even integers
 * except for a single integer N. Write a method that takes the array as an argument and returns this "outlier" N.
 */

import static java.lang.Math.abs;
import static java.util.Arrays.stream;

public class FindOutlier {

    protected int findOutlier(int[] integers) {

        int count = 0;
        // Adding condition to convert -1 to 1
        int rem = abs(integers[0] % 2);

        for (int i = 1; i < integers.length; i++) {
            if (abs(integers[i] % 2) != rem) {
                // Return previous loop value
                if (count >= 1) return integers[i - 1];
                // Return automatically last index
                if (i == integers.length - 1) return integers[i];

                rem = abs(integers[i] % 2);
                count++;
            }
        }

        return integers[0];
    }

    protected int findOutlierUsingStream(int[] integers) {

        int sum = stream(integers).limit(3).map(i -> abs(i) % 2).sum();
        int mod = (sum == 0 || sum == 1) ? 1 : 0;

        return stream(integers).parallel().filter(n -> abs(n) % 2 == mod).findFirst().orElse(0);
    }
}
