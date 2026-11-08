package com.teb.practice.analysis;

import java.util.List;

public record BorrowingAnalysis(
        int totalBooksBorrowed, List<String> borrowedBooks, String status) {}
