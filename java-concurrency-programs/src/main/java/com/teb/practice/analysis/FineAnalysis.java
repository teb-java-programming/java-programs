package com.teb.practice.analysis;

import java.math.BigDecimal;
import java.util.List;

public record FineAnalysis(
        int totalPendingFines,
        BigDecimal totalOutstandingAmount,
        List<BigDecimal> pendingFines,
        String status) {}
