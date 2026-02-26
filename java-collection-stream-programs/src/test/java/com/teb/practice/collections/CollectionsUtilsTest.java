package com.teb.practice.collections;

import static com.teb.practice.constants.Constants.WORDS;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

class CollectionsUtilsTest {

    private static final List<String> RANDOM_WORDS = WORDS.subList(100, 110);
    private static final List<Integer> RANDOM_NUMBERS = List.of(8, 32, 2, 16, 0, 4);

    @Test
    void testUsingCollectionsShuffle() {

        List<String> words = new ArrayList<>(RANDOM_WORDS);

        // shuffle() works on mutable collections and randomises the sequence of items
        Collections.shuffle(words, new Random(1600));

        assertNotEquals(RANDOM_WORDS, words);
    }

    @Test
    void testUsingCollectionsMax() {

        // max() returns the item with the highest value of character or number
        assertEquals("grunge", Collections.max(RANDOM_WORDS));
        assertEquals(32, Collections.max(RANDOM_NUMBERS));
    }
}
