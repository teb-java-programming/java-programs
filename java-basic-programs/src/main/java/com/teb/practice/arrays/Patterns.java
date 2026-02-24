package com.teb.practice.arrays;

import static com.teb.practice.constants.Constants.SCAN;

import static java.lang.System.out;

public class Patterns {

    private static void topLeft(int limit) {

        for (int i = 1; i <= limit; i++) {
            for (int j = 0; j < limit; j++) {
                if ((j < i)) {
                    out.print("# ");
                } else {
                    out.print("  ");
                }
            }
            out.println();
        }
    }

    private static void topRight(int limit) {

        for (int i = 1; i <= limit; i++) {
            for (int j = 0; j < limit; j++) {
                if ((j > limit - i - 1)) {
                    out.print("# ");
                } else {
                    out.print("  ");
                }
            }
            out.println();
        }
    }

    private static void bottomLeft(int limit) {

        for (int i = 0; i < limit; i++) {
            for (int j = 0; j < limit; j++) {
                if ((j < limit - i)) {
                    out.print("# ");
                } else {
                    out.print("  ");
                }
            }
            out.println();
        }
    }

    private static void bottomRight(int limit) {

        for (int i = 1; i <= limit; i++) {
            for (int j = 0; j < limit; j++) {
                if ((j >= i - 1)) {
                    out.print("# ");
                } else {
                    out.print("  ");
                }
            }
            out.println();
        }
    }

    public static void main(String[] args) {

        out.print("Enter the pattern limit: ");
        int limit = SCAN.nextInt();

        out.println("Enter the starting point, select:");
        out.println("1. top-left");
        out.println("2. top-right");
        out.println("3. bottom-left");
        out.println("4. bottom-right");
        out.print("Your choice: ");
        int choice = SCAN.nextInt();

        switch (choice) {
            case 1:
                topLeft(limit);
                break;
            case 2:
                topRight(limit);
                break;
            case 3:
                bottomLeft(limit);
                break;
            case 4:
                bottomRight(limit);
                break;
            default:
                out.println("Invalid option!");
        }
    }
}
