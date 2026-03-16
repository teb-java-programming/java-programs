package com.teb.practice.streams;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static java.util.stream.IntStream.of;
import static java.util.stream.IntStream.range;
import static java.util.stream.IntStream.rangeClosed;

import org.junit.jupiter.api.Test;

import java.util.IntSummaryStatistics;
import java.util.List;

class NumberStreamPracticeTest {

    @Test
    void testUsingRangeSum() {

        // range() defines the limit of a stream and sum() returns the aggregated value
        int result = range(1, 10).sum();

        assertEquals(45, result);
    }

    @Test
    void testUsingRangeClosedSum() {

        // rangeClosed() defines the limit including the upper limit
        int result = rangeClosed(1, 10).sum();

        assertEquals(55, result);
    }

    @Test
    void testUsingMaxMinAverage() {

        // max() returns the highest value in a stream
        int max = rangeClosed(1, 10).max().orElseThrow();

        // min() returns the lowest value in a stream
        int min = rangeClosed(1, 10).min().orElseThrow();

        // average() returns the average value in a stream
        double average = rangeClosed(1, 10).average().orElseThrow();

        assertEquals(10, max);
        assertEquals(1, min);
        assertEquals(5.5, average);
    }

    @Test
    void testUsingFilterMap() {

        // filter() returns stream result and map() returns a transformed stream
        int result = rangeClosed(1, 10).filter(x -> x % 4 == 0).map(x -> x + 2).sum();

        assertEquals(16, result);
    }

    @Test
    void testUsingLimitSkipBoxed() {

        // limit() trims a stream and skip() eliminates values from the start of the stream while
        // boxed() converts a primitive type reference to an object reference
        List<Integer> result = of(rangeClosed(1, 10).limit(5).skip(3).toArray()).boxed().toList();

        assertEquals(2, result.size());
        assertEquals(4, result.getFirst());
    }

    @Test
    void testUsingSummaryStatistics() {

        // summaryStatistics() collects all metrics under one IntSummaryStatistics reference
        IntSummaryStatistics intStat = rangeClosed(1, 10).summaryStatistics();

        assertEquals(10, intStat.getCount());
        assertEquals(55, intStat.getSum());
        assertEquals(5.5, intStat.getAverage());
        assertEquals(1, intStat.getMin());
        assertEquals(10, intStat.getMax());
    }
}
