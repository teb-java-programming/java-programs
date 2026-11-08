package com.teb.practice.service;

import static org.apache.commons.collections4.CollectionUtils.isEmpty;

import static java.util.Collections.emptyList;

import com.teb.practice.analysis.BorrowingAnalysis;
import com.teb.practice.model.LibraryMember;

import java.util.List;

public class BorrowingAnalysisService {

    public BorrowingAnalysis analyse(LibraryMember member) {

        List<String> borrowedBooks =
                isEmpty(member.borrowedBooks()) ? emptyList() : member.borrowedBooks();
        int totalBooksBorrowed = borrowedBooks.size();
        String status = totalBooksBorrowed == 0 ? "NO_BOOKS_BORROWED" : "BOOKS_BORROWED";

        return new BorrowingAnalysis(totalBooksBorrowed, borrowedBooks, status);
    }
}
