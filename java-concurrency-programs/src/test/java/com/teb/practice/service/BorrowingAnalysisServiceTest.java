package com.teb.practice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import com.teb.practice.analysis.BorrowingAnalysis;
import com.teb.practice.model.LibraryMember;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

class BorrowingAnalysisServiceTest {

    private final BorrowingAnalysisService service = new BorrowingAnalysisService();

    private static Stream<Arguments> borrowingScenarioProvider() {

        return Stream.of(
                arguments(
                        List.of("The Hobbit", "Dune"),
                        new BorrowingAnalysis(2, List.of("The Hobbit", "Dune"), "BOOKS_BORROWED")),
                arguments(List.of(), new BorrowingAnalysis(0, List.of(), "NO_BOOKS_BORROWED")),
                arguments(null, new BorrowingAnalysis(0, List.of(), "NO_BOOKS_BORROWED")));
    }

    @ParameterizedTest
    @MethodSource("borrowingScenarioProvider")
    void testBorrowedBooks(List<String> books, BorrowingAnalysis expected) {

        assertEquals(
                expected, service.analyse(LibraryMember.builder().borrowedBooks(books).build()));
    }
}
