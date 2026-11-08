package com.teb.practice.service;

import static org.apache.commons.collections4.CollectionUtils.isEmpty;

import static java.math.BigDecimal.ZERO;
import static java.util.Collections.emptyList;

import com.teb.practice.analysis.FineAnalysis;
import com.teb.practice.model.LibraryMember;

import java.math.BigDecimal;
import java.util.List;

public class FineAnalysisService {

    public FineAnalysis analyse(LibraryMember member) {

        List<BigDecimal> pendingFines =
                isEmpty(member.pendingFines()) ? emptyList() : member.pendingFines();
        BigDecimal totalOutstandingAmount = pendingFines.stream().reduce(ZERO, BigDecimal::add);
        String status =
                totalOutstandingAmount.compareTo(ZERO) == 0
                        ? "NO_OUTSTANDING_FINES"
                        : "OUTSTANDING_FINES";

        return new FineAnalysis(pendingFines.size(), totalOutstandingAmount, pendingFines, status);
    }
}
