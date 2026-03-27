package com.teb.practice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

class ALinkedListTest {

    private final ALinkedList aLinkedList = new ALinkedList();

    static Stream<Arguments> nodeProvider() {

        return Stream.of(
                Arguments.of(List.of(1.0, 2.0, 3.0, 4.0), List.of(0.12, 0.24, 0.36, 0.48)),
                Arguments.of(new ArrayList<>(), new ArrayList<>()),
                Arguments.of(null, new ArrayList<>()));
    }

    @ParameterizedTest
    @MethodSource("nodeProvider")
    void testLinkedList(List<Double> inputList, List<Double> resultList) {

        aLinkedList.insert(inputList);
        assertEquals(resultList, aLinkedList.display());
    }
}
