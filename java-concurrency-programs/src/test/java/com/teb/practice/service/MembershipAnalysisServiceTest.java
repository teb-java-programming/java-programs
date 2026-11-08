package com.teb.practice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import static java.time.LocalDate.now;

import com.teb.practice.analysis.MembershipAnalysis;
import com.teb.practice.model.LibraryMember;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class MembershipAnalysisServiceTest {

    private final MembershipAnalysisService service = new MembershipAnalysisService();

    private static Stream<Arguments> membershipScenarioProvider() {

        return Stream.of(
                arguments(364L, "NEW_MEMBER"),
                arguments(365L, "REGULAR_MEMBER"),
                arguments(1824L, "REGULAR_MEMBER"),
                arguments(1825L, "LONG_STANDING_MEMBER"));
    }

    @ParameterizedTest
    @MethodSource("membershipScenarioProvider")
    void testMembershipDuration(long membershipDays, String expectedClassification) {

        assertEquals(
                new MembershipAnalysis(membershipDays, expectedClassification),
                service.analyse(
                        LibraryMember.builder()
                                .memberSince(now().minusDays(membershipDays))
                                .build()));
    }
}
