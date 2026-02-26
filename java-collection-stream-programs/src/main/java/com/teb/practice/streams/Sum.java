package com.teb.practice.streams;

import static java.lang.System.out;
import static java.util.Arrays.asList;
import static java.util.stream.IntStream.of;

import java.util.List;

public class Sum {

    private static void sumIntArray(int[] integerArray) {

        out.println("Using IntStream class...");
        out.println(of(integerArray).sum());
    }

    private static void sumIntegerList(List<Integer> integerList) {

        out.println(integerList.stream().mapToInt(Integer::intValue).sum());
    }

    private static void sumLongList(List<Long> longList) {

        out.println(longList.stream().mapToLong(Long::longValue).sum());
    }

    public static void main(String[] args) {

        int[] integerArray = {2, 1, 16, 4, 8};
        List<Long> longList =
                asList(1000000002L, 2000000004L, 4000000008L, 8000000016L, 16000000032L);

        sumIntArray(integerArray);
        sumIntegerList(of(integerArray).boxed().toList());
        sumLongList(longList);
    }
}
