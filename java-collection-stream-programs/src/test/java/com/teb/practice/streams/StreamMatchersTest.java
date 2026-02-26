package com.teb.practice.streams;

import static com.teb.practice.constants.Constants.SPACE;
import static com.teb.practice.constants.Constants.WORDS;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.List;

class StreamMatchersTest {

    @Test
    void testUsingStreamFilter() {

        // filter() returns stream result when condition is satisfied
        List<String> result = WORDS.stream().filter(word -> word.startsWith("ki")).toList();

        assertTrue(CollectionUtils.isNotEmpty(result));
        assertEquals(5, result.size());
    }

    @Test
    void testUsingStreamAnyMatch() {

        // anyMatch() returns true when condition is satisfied by at least one list item
        assertTrue(WORDS.stream().anyMatch(word -> word.startsWith("ki")));
    }

    @Test
    void testUsingStreamAllMatch() {

        // allMatch() returns true only when condition is satisfied by all list items
        assertFalse(WORDS.stream().allMatch(word -> word.startsWith("ki")));
    }

    @Test
    void testUsingStreamFindFirst() {

        // findFirst() returns the first item from a list when condition is satisfied
        String result = WORDS.stream().filter(word -> word.endsWith("a")).findFirst().orElse(SPACE);

        assertTrue(StringUtils.isNotEmpty(result));
        assertEquals("aorta", result);
    }

    @Test
    void testUsingStreamFindAny() {

        // findAny() returns a random item from a list when condition is satisfied
        String result =
                WORDS.parallelStream().filter(word -> word.endsWith("a")).findAny().orElse(SPACE);

        assertTrue(StringUtils.isNotEmpty(result));
    }
}
