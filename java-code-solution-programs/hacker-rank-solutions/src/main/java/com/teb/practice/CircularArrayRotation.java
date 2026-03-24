package com.teb.practice;

/*
 * John Watson knows of an operation called a right circular rotation on an array of integers.
 * One rotation operation moves the last array element to the first position and shifts all remaining elements right one.
 * To test Sherlock's abilities, Watson provides Sherlock with an array of integers.
 * Sherlock is to perform the rotation operation a number of times then determine the value of the element at a given position.
 * For each array, perform a number of right circular rotations and return the values of the elements at the given indices.
 */

import static com.teb.practice.constants.Constants.SCAN;
import static com.teb.practice.constants.Constants.SPACE;

import static java.lang.System.out;
import static java.util.Arrays.stream;
import static java.util.Collections.rotate;

import java.util.ArrayList;
import java.util.List;

public class CircularArrayRotation {

    private static List<Integer> circularArrayRotation(
            List<Integer> inputList, int rotationIndex, List<Integer> queriesList) {

        List<Integer> result = new ArrayList<>();

        rotate(inputList, rotationIndex);
        queriesList.forEach(index -> result.add(inputList.get(index)));

        return result;
    }

    public static void main(String[] args) {

        List<Integer> inputList = new ArrayList<>();
        List<Integer> queriesList = new ArrayList<>();

        out.print("Enter the initial array separated by single space: ");
        String input = SCAN.nextLine();
        out.print("Enter the queried index as array separated by single space: ");
        String queries = SCAN.nextLine();
        out.print("Enter the number of rotations: ");
        int index = SCAN.nextInt();

        String[] stringInput = input.split(SPACE);
        stream(stringInput).forEach(string -> inputList.add(Integer.valueOf(string)));
        String[] queriesInput = queries.split(SPACE);
        stream(queriesInput).forEach(string -> queriesList.add(Integer.valueOf(string)));

        out.println("Result: " + circularArrayRotation(inputList, index, queriesList));
    }
}
