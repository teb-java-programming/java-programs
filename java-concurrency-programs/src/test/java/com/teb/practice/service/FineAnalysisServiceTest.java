package com.teb.practice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import com.teb.practice.analysis.FineAnalysis;
import com.teb.practice.model.LibraryMember;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

class FineAnalysisServiceTest {

    private final FineAnalysisService service = new FineAnalysisService();

    private static Stream<Arguments> fineScenarioProvider() {

        return Stream.of(
                arguments(
                        List.of(new BigDecimal("2.50"), new BigDecimal("1.75")),
                        new FineAnalysis(
                                2,
                                new BigDecimal("4.25"),
                                List.of(new BigDecimal("2.50"), new BigDecimal("1.75")),
                                "OUTSTANDING_FINES")),
                arguments(
                        List.of(),
                        new FineAnalysis(0, BigDecimal.ZERO, List.of(), "NO_OUTSTANDING_FINES")),
                arguments(
                        null,
                        new FineAnalysis(0, BigDecimal.ZERO, List.of(), "NO_OUTSTANDING_FINES")));
    }

    @ParameterizedTest
    @MethodSource("fineScenarioProvider")
    void testOutstandingFines(List<BigDecimal> fines, FineAnalysis expected) {

        assertEquals(
                expected, service.analyse(LibraryMember.builder().pendingFines(fines).build()));
    }
}
