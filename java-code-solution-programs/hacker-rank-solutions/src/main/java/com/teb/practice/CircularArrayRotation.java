package com.teb.practice;

/*
 * John Watson knows of an operation called a right circular rotation on an array of integers.
 * One rotation operation moves the last array element to the first position and shifts all remaining elements right one.
 * To test Sherlock's abilities, Watson provides Sherlock with an array of integers.
 * Sherlock is to perform the rotation operation a number of times then determine the value of the element at a given position.
 * For each array, perform a number of right circular rotations and return the values of the elements at the given indices.
 */

import static java.util.Collections.rotate;

import java.util.ArrayList;
import java.util.List;

public class CircularArrayRotation {

    protected List<Integer> rotateArray(
            List<Integer> inputList, List<Integer> queriesList, int rotationIndex) {

        List<Integer> mutableList = new ArrayList<>(inputList);
        List<Integer> result = new ArrayList<>();

        rotate(mutableList, rotationIndex);
        queriesList.forEach(index -> result.add(mutableList.get(index)));

        return result;
    }
}
