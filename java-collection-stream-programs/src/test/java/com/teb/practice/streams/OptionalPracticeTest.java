package com.teb.practice.streams;

import static com.teb.practice.constants.Constants.WORDS;

import static org.apache.commons.collections4.CollectionUtils.isEmpty;
import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class OptionalPracticeTest {

    private static final List<String> RANDOM_WORDS = WORDS.subList(300, 310);

    @Test
    void testOptionalOf() {

        List<String> result =
                // of() is used in case an entry does not exist
                Optional.of(RANDOM_WORDS).stream()
                        .flatMap(Collection::stream)
                        .filter(x -> x.length() > 5)
                        .toList();

        assertTrue(isNotEmpty(result));
        assertEquals(6, result.size());
    }

    @Test
    void testOptionalOfNullable() {

        List<String> result =
                // ofNullable() is used to check if the list itself is null
                Optional.ofNullable(getNullList()).stream()
                        .flatMap(Collection::stream)
                        .filter(x -> x.length() > 1)
                        .toList();

        assertTrue(isEmpty(result));
    }

    private List<String> getNullList() {

        return null;
    }
}
