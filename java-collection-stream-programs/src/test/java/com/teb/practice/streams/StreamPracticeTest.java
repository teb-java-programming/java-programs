package com.teb.practice.streams;

import static com.teb.practice.constants.Constants.SPACE;
import static com.teb.practice.constants.Constants.WORDS;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.concat;
import static java.util.stream.Stream.generate;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

class StreamPracticeTest {

    private static final List<String> RANDOM_WORDS = WORDS.subList(200, 210);

    @Test
    void testUsingStreamFilter() {

        // filter() returns stream result when condition is satisfied
        List<String> result = RANDOM_WORDS.stream().filter(word -> word.startsWith("mo")).toList();

        assertTrue(CollectionUtils.isNotEmpty(result));
        assertEquals(3, result.size());
    }

    @Test
    void testUsingStreamAnyMatch() {

        // anyMatch() returns true when condition is satisfied by at least one list item
        assertTrue(RANDOM_WORDS.stream().anyMatch(word -> word.startsWith("mo")));
    }

    @Test
    void testUsingStreamAllMatch() {

        // allMatch() returns true only when condition is satisfied by all list items
        assertFalse(RANDOM_WORDS.stream().allMatch(word -> word.startsWith("mo")));
    }

    @Test
    void testUsingStreamFindFirst() {

        // findFirst() returns the first item from a list when condition is satisfied
        String result =
                RANDOM_WORDS.stream().filter(word -> word.endsWith("e")).findFirst().orElse(SPACE);

        assertTrue(StringUtils.isNotEmpty(result));
        assertEquals("magazine", result);
    }

    @Test
    void testUsingStreamFindAny() {

        // findAny() returns a random item from a list when condition is satisfied
        String result =
                RANDOM_WORDS.parallelStream()
                        .filter(word -> word.endsWith("e"))
                        .findAny()
                        .orElse(SPACE);

        assertTrue(StringUtils.isNotEmpty(result));
    }

    @Test
    void testUsingStreamConcat() {

        List<Integer> fibonacci = new ArrayList<>(List.of(0, 1, 1, 2, 3, 5, 8, 13, 21, 34));
        List<Integer> numbers = new ArrayList<>(List.of(55, 89, 144));

        // concat() returns a merged stream of two collection streams
        List<Integer> result = concat(fibonacci.stream(), numbers.stream()).collect(toList());

        assertEquals(13, result.size());
        assertTrue(CollectionUtils.isNotEmpty(result));
    }

    @Test
    void testUsingStreamGenerate() {

        Random random = new Random(1000);
        AtomicInteger counter = new AtomicInteger(10);

        // generate() returns an infinite sequential stream regulated by limit()
        List<Integer> randomResult = generate(random::nextInt).limit(5).toList();
        List<Integer> atomicResult = generate(counter::incrementAndGet).limit(5).toList();

        assertEquals(5, randomResult.size());
        assertEquals(15, atomicResult.getLast());
    }
}
