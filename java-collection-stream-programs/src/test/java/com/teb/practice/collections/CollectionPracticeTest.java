package com.teb.practice.collections;

import static com.teb.practice.constants.Constants.WORDS;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static java.util.Collections.addAll;
import static java.util.Collections.binarySearch;
import static java.util.Collections.copy;
import static java.util.Collections.disjoint;
import static java.util.Collections.frequency;
import static java.util.Collections.max;
import static java.util.Collections.min;
import static java.util.Collections.shuffle;
import static java.util.Collections.sort;
import static java.util.Collections.swap;
import static java.util.Comparator.comparing;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

class CollectionPracticeTest {

    private static final List<Integer> RANDOM_NUMBERS = List.of(8, 32, 2, 128, 16, 0, 4, 64);
    private static final List<String> RANDOM_WORDS = WORDS.subList(100, 110);

    // Mutable versions of the lists
    List<Integer> numbers = new ArrayList<>(RANDOM_NUMBERS);
    List<String> words = new ArrayList<>(RANDOM_WORDS);

    @Test
    void testUsingCollections() {

        // min() returns the element with the lowest value of character or number
        assertEquals(0, min(RANDOM_NUMBERS));
        assertEquals("garbage", min(RANDOM_WORDS));

        // max() returns the element with the highest value of character or number
        assertEquals(128, max(RANDOM_NUMBERS));
        assertEquals("grunge", max(RANDOM_WORDS));

        // disjoint() returns true when two collections have no common elements
        assertTrue(disjoint(RANDOM_NUMBERS, RANDOM_WORDS));

        // copy() replaces values in destination list at same indices as source list
        copy(numbers, List.of(36, 48, 96));
        assertEquals(36, numbers.getFirst());

        // addAll() adds individual values to an existing collection
        addAll(numbers, 36, 8);
        assertEquals(10, numbers.size());

        // frequency() counts the occurrences of an element in the list
        assertEquals(2, frequency(numbers, 36));
    }

    @Test
    void testUsingCollectionsSwap() {

        assertEquals(16, numbers.get(4));
        assertEquals(4, numbers.get(6));

        // swap() swaps the value between two given indices
        swap(numbers, 4, 6);

        assertEquals(4, numbers.get(4));
        assertEquals(16, numbers.get(6));
    }

    @Test
    void testUsingCollectionsShuffle() {

        assertEquals(RANDOM_WORDS, words);

        // shuffle() randomises the sequence of elements in a mutable collection
        shuffle(words, new Random(100));

        assertNotEquals(RANDOM_WORDS, words);
    }

    @Test
    void testUsingCollectionsSort() {

        assertEquals(8, numbers.getFirst());
        assertEquals("gimbal", words.getFirst());

        // sort() orders the sequence of elements in a mutable collection
        sort(numbers);
        sort(words);

        assertNotEquals(8, numbers.getFirst());
        assertEquals("garbage", words.getFirst());
    }

    @Test
    void testUsingCollectionsIndexedBinarySearch() {

        // binarySearch() calls indexedBinarySearch() for collections implementing RandomAccess to
        // search for a key and returns its index in a sorted list - O(log n)
        assertNotEquals(4, binarySearch(RANDOM_NUMBERS, 16));
        assertEquals(1, binarySearch(RANDOM_WORDS.stream().sorted().toList(), "genetics"));
    }

    @Test
    void testUsingCollectionsIteratorBinarySearch() {

        List<Integer> numbers = new LinkedList<>();

        IntStream.range(0, 5001).map(i -> i + 4).forEach(numbers::add);

        // binarySearch() calls iteratorBinarySearch() for collections not implementing RandomAccess
        // to search for a key and returns its index in a sorted list - O(n)
        assertEquals(2044, binarySearch(numbers, 2048));
    }

    @Test
    void testUsingCollectionsBinarySearchWithComparator() {

        // Comparators declared to compare based on String length and absolute value of Integer
        Comparator<Integer> absoluteComparator = comparing(Math::abs);
        Comparator<String> lengthComparator = comparing(String::length);

        addAll(numbers, -5, -9);
        numbers.sort(absoluteComparator);
        words.sort(lengthComparator);

        // binarySearch() with a comparator argument searches for a key in a list sorted using the
        // same comparator
        int numberIndex = binarySearch(numbers, 8, absoluteComparator);
        int wordIndex = binarySearch(words, "--------", lengthComparator);

        assertNotEquals(0, numberIndex);
        assertEquals(8, words.get(wordIndex).length());
    }
}
