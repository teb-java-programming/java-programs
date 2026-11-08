package com.teb.practice.concurrency;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static java.time.LocalDate.now;
import static java.util.concurrent.Executors.newFixedThreadPool;

import com.teb.practice.analysis.MemberReport;
import com.teb.practice.model.LibraryMember;
import com.teb.practice.service.BorrowingAnalysisService;
import com.teb.practice.service.FineAnalysisService;
import com.teb.practice.service.MembershipAnalysisService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutorService;

class BatchProcessingServiceTest {

    private ExecutorService executorService;
    private BatchProcessingService batchProcessingService;

    @BeforeEach
    void setUp() {

        executorService = newFixedThreadPool(4);
        batchProcessingService =
                new BatchProcessingService(
                        executorService,
                        new MembershipAnalysisService(),
                        new BorrowingAnalysisService(),
                        new FineAnalysisService());
    }

    @AfterEach
    void tearDown() {

        executorService.shutdown();
    }

    @Test
    void testBatchProcessingAndPreserveInputOrder() {

        List<LibraryMember> members =
                List.of(
                        createMember("ILM001", "Mikołaj Wierzbicki"),
                        createMember("ILM002", "Aoife Ní Bhriain"));

        List<MemberReport> reports = batchProcessingService.processBatch(members).join();

        assertEquals(2, reports.size());
        assertEquals("ILM001", reports.getFirst().memberId());
        assertEquals("Aoife Ní Bhriain", reports.getLast().memberName());
    }

    @Test
    void testEmptyListReturnedWhenBatchIsEmpty() {

        assertEquals(List.of(), batchProcessingService.processBatch(List.of()).join());
    }

    @Test
    void testAnalysisFailurePropagation() {

        LibraryMember invalidMember =
                LibraryMember.builder()
                        .id("ILM003")
                        .name("Invalid")
                        .memberSince(null)
                        .borrowedBooks(List.of())
                        .pendingFines(List.of())
                        .build();

        assertThrows(
                CompletionException.class,
                () -> batchProcessingService.processBatch(List.of(invalidMember)).join());
    }

    private LibraryMember createMember(String id, String name) {

        return LibraryMember.builder()
                .id(id)
                .name(name)
                .memberSince(now().minusDays(100))
                .borrowedBooks(List.of())
                .pendingFines(List.of())
                .build();
    }
}
