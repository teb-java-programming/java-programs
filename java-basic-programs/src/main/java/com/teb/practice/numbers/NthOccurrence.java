package com.teb.practice.numbers;

import static com.teb.practice.constants.Constants.SCAN;

import static java.lang.System.out;

public class NthOccurrence {

    private static int getOccurrence(String target, String search, int occur) {

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

    public static void main(String[] args) {

        out.print("Enter string to search in: ");
        String target = SCAN.nextLine();
        out.print("Enter string to search: ");
        String search = SCAN.next();
        out.print("Enter required occurrence: ");
        int occur = SCAN.nextInt();
        int result = getOccurrence(target, search, occur);

        if (result == 0) out.println("The desired occurrence of the string was not found.");
        else out.printf("The required index of '%s' in '%s' is %d", search, target, result);
    }
}
