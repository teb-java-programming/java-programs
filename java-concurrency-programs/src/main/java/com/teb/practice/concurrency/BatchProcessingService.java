package com.teb.practice.concurrency;

import static java.util.concurrent.CompletableFuture.allOf;
import static java.util.concurrent.CompletableFuture.supplyAsync;

import com.teb.practice.analysis.BorrowingAnalysis;
import com.teb.practice.analysis.FineAnalysis;
import com.teb.practice.analysis.MemberReport;
import com.teb.practice.analysis.MembershipAnalysis;
import com.teb.practice.model.LibraryMember;
import com.teb.practice.service.BorrowingAnalysisService;
import com.teb.practice.service.FineAnalysisService;
import com.teb.practice.service.MembershipAnalysisService;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

@RequiredArgsConstructor
public class BatchProcessingService {

    // Shared executor responsible for managing the worker threads.
    private final ExecutorService executorService;

    // Individual services responsible for the three independent analyses.
    private final MembershipAnalysisService membershipAnalysisService;
    private final BorrowingAnalysisService borrowingAnalysisService;
    private final FineAnalysisService fineAnalysisService;

    public CompletableFuture<List<MemberReport>> processBatch(List<LibraryMember> members) {

        // Submit each member for processing without blocking the calling thread
        // Each member's three analyses are independently submitted to the executor
        List<CompletableFuture<MemberReport>> reportFutures =
                members.stream().map(this::processMember).toList();

        // Create a future that completes when every member report is ready
        CompletableFuture<Void> allReports = allOf(reportFutures.toArray(new CompletableFuture[0]));

        // Once all reports are complete, collect their results into a single list
        // join() retrieves completed results without introducing another blocking wait
        return allReports.thenApply(
                ignored -> reportFutures.stream().map(CompletableFuture::join).toList());
    }

    private CompletableFuture<MemberReport> processMember(LibraryMember member) {

        // Submit membership, borrowing and fine analysis independently to the shared executor
        CompletableFuture<MembershipAnalysis> membershipFuture =
                supplyAsync(() -> membershipAnalysisService.analyse(member), executorService);

        CompletableFuture<BorrowingAnalysis> borrowingFuture =
                supplyAsync(() -> borrowingAnalysisService.analyse(member), executorService);

        CompletableFuture<FineAnalysis> fineFuture =
                supplyAsync(() -> fineAnalysisService.analyse(member), executorService);

        // Wait for all three analyses to complete before combining their results.
        return allOf(membershipFuture, borrowingFuture, fineFuture)
                .thenApply(ignored ->
                        new MemberReport(
                                member.id(),
                                member.name(),
                                membershipFuture.join(),
                                borrowingFuture.join(),
                                fineFuture.join()));
    }
}
