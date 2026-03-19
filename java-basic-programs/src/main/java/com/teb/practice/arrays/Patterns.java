package com.teb.practice.arrays;

public class Patterns {

    protected String generate(int limit, Direction direction) {

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < limit; i++) {
            for (int j = 0; j < limit; j++) {
                boolean printHash =
                        switch (direction) {
                            case TOP_LEFT -> j <= i;
                            case TOP_RIGHT -> j >= limit - i - 1;
                            case BOTTOM_LEFT -> j < limit - i;
                            case BOTTOM_RIGHT -> j >= i;
                        };
                builder.append(printHash ? "#\t" : "\t");
            }
            builder.append("\n");
        }

        return builder.toString();
    }

    protected enum Direction {
        TOP_LEFT,
        TOP_RIGHT,
        BOTTOM_LEFT,
        BOTTOM_RIGHT
    }
}
