package com.teb.practice.numbers;

import static java.lang.System.out;

import java.util.Arrays;

public class OutlierNumber {

    static int findOutlier(int[] integers) {

        int count = 0;
        // Adding condition to convert -1 to 1
        int rem = Math.abs(integers[0] % 2);

        for (int i = 1; i < integers.length; i++) {
            if (Math.abs(integers[i] % 2) != rem) {
                // Return previous loop value
                if (count >= 1) return integers[i - 1];
                // Return automatically last index
                if (i == integers.length - 1) return integers[i];

                rem = Math.abs(integers[i] % 2);
                count++;
            }
        }

        return integers[0];
    }

    static int findOutlierUsingStream(int[] integers) {

        int sum = Arrays.stream(integers).limit(3).map(i -> Math.abs(i) % 2).sum();
        int mod = (sum == 0 || sum == 1) ? 1 : 0;

        return Arrays.stream(integers)
                .parallel()
                .filter(n -> Math.abs(n) % 2 == mod)
                .findFirst()
                .getAsInt();
    }

    public static void main(String[] args) {

        int[] arrayOne = {2, 6, 8, -10, 3};
        int[] arrayTwo = {206, 121, 7, 17, 1901, 221, 7, 1, 351, 1, 71};

        out.println("Outlier value: " + findOutlier(arrayOne));
        out.println("Outlier value (using Stream): " + findOutlierUsingStream(arrayTwo));
    }
}
