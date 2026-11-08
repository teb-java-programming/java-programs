package com.teb.practice.service;

import static java.time.LocalDate.now;
import static java.time.temporal.ChronoUnit.DAYS;

import com.teb.practice.analysis.MembershipAnalysis;
import com.teb.practice.model.LibraryMember;

public class MembershipAnalysisService {

    public MembershipAnalysis analyse(LibraryMember member) {

        long membershipDuration = DAYS.between(member.memberSince(), now());
        String classification;

        if (membershipDuration < 365) classification = "NEW_MEMBER";
        else if (membershipDuration < 1825) classification = "REGULAR_MEMBER";
        else classification = "LONG_STANDING_MEMBER";

        return new MembershipAnalysis(membershipDuration, classification);
    }
}
