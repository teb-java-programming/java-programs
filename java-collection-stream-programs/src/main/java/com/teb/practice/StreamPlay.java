package com.teb.practice;

import static java.lang.System.out;
import static java.util.Arrays.stream;
import static java.util.stream.Stream.of;

import java.util.Arrays;
import java.util.List;

public class StreamPlay {

    // Reverse each element of the array
    private static void reverseStream(String[] array) {

        out.println("Reversing...");
        of(array).forEach(index -> out.println(new StringBuilder(index).reverse()));
    }

    // Sort the elements in an array
    private static void sortStream(String[] array) {

        out.println("Sorting...");
        of(array).sorted().forEach(out::println);
    }

    // Filtering elements based on condition
    private static void filterStream(String[] array) {

        out.println("Filtering...");
        stream(array).filter(index -> index.startsWith("h")).sorted().forEach(out::println);
    }

    // Formatting elements in an array
    private static void formatStream(String[] array) {

        out.println("Formatting...");
        of(array).map(String::toUpperCase).sorted().forEach(out::println);
    }

    // Stream used with List class
    private static void listStream(String[] array) {

        out.println("Using List class...");
        List<String> stringList = Arrays.asList(array);
        stringList.forEach(out::println);
    }

    public static void main(String[] args) {

        String[] stringArrayOne = {"dw", "az", "by", "cx"};
        String[] stringArrayTwo = {"hit", "kit", "his", "lit"};

        reverseStream(stringArrayOne);
        sortStream(stringArrayTwo);
        filterStream(stringArrayTwo);
        formatStream(stringArrayOne);
        listStream(stringArrayTwo);
    }
}
