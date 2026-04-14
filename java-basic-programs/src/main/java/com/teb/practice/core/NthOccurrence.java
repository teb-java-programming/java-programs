package com.teb.practice.core;

public class NthOccurrence {

    protected int getOccurrence(String target, String search, int occur) {

        String tempString = target;
        int tempIndex;
        int finalIndex = 0;
        int counter = 0;

        for (int i = 0; i < occur; i++) {
            tempIndex = tempString.indexOf(search);

            if (tempIndex == -1) {
                break;
            }

            tempString = tempString.substring(++tempIndex);
            finalIndex += tempIndex;
            counter++;
        }

        if (counter < occur) return 0;
        else return --finalIndex;
    }
}
