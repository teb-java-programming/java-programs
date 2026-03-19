package com.teb.practice.arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PatternsTest {

    private final Patterns patterns = new Patterns();

    @Test
    void testPatterns() {

        String topLeft = "#\t\t\t\t\n#\t#\t\t\t\n#\t#\t#\t\t\n#\t#\t#\t#\t\n";
        String topRight =
                "\t\t\t\t#\t\n\t\t\t#\t#\t\n\t\t#\t#\t#\t\n\t#\t#\t#\t#\t\n#\t#\t#\t#\t#\t\n";
        String bottomLeft = "#\t#\t#\t\n#\t#\t\t\n#\t\t\t\n";
        String bottomRight = "#\t#\t#\t#\t\n\t#\t#\t#\t\n\t\t#\t#\t\n\t\t\t#\t\n";

        assertEquals(topLeft, patterns.generate(4, Patterns.Direction.TOP_LEFT));
        assertEquals(topRight, patterns.generate(5, Patterns.Direction.TOP_RIGHT));
        assertEquals(bottomLeft, patterns.generate(3, Patterns.Direction.BOTTOM_LEFT));
        assertEquals(bottomRight, patterns.generate(4, Patterns.Direction.BOTTOM_RIGHT));
    }
}
