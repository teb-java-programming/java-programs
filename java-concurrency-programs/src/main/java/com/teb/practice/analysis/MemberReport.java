package com.teb.practice.analysis;

public record MemberReport(
        String memberId,
        String memberName,
        MembershipAnalysis membershipAnalysis,
        BorrowingAnalysis borrowingAnalysis,
        FineAnalysis fineAnalysis) {}
