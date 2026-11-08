package com.teb.practice.model;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Builder
public record LibraryMember(
        String id,
        String name,
        Experience experience,
        String region,
        LocalDate memberSince,
        List<String> borrowedBooks,
        List<BigDecimal> pendingFines) {}
