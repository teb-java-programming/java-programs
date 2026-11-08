package com.teb.practice.concurrency;

import static com.teb.practice.config.JacksonConfig.createJsonMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static java.math.BigDecimal.ZERO;

import com.teb.practice.analysis.MemberReport;
import com.teb.practice.model.LibraryMember;
import com.teb.practice.service.BorrowingAnalysisService;
import com.teb.practice.service.FineAnalysisService;
import com.teb.practice.service.MembershipAnalysisService;

import org.junit.jupiter.api.Test;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.json.JsonMapper;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class BatchProcessingIntegrationTest {

    @Test
    void testDeserialisationAndSuccessfulBatchProcessing() throws Exception {

        JsonMapper jsonMapper = createJsonMapper();
        List<LibraryMember> members;

        try (InputStream inputStream = getClass().getResourceAsStream("/batch.json")) {
            assertNotNull(inputStream);
            members = jsonMapper.readValue(inputStream, new TypeReference<>() {});
        }

        assertEquals(12, members.size());

        try (ExecutorService executorService = Executors.newFixedThreadPool(4)) {
            BatchProcessingService batchProcessingService =
                    new BatchProcessingService(
                            executorService,
                            new MembershipAnalysisService(),
                            new BorrowingAnalysisService(),
                            new FineAnalysisService());

            List<MemberReport> reports = batchProcessingService.processBatch(members).join();

            assertEquals(12, reports.size());

            for (int i = 0; i < reports.size(); i++) {
                assertEquals(members.get(i).id(), reports.get(i).memberId());
            }

            // Normal data
            MemberReport tarniReport = reports.get(7);

            assertEquals(2, tarniReport.borrowingAnalysis().totalBooksBorrowed());
            assertEquals(
                    new BigDecimal("4.50"), tarniReport.fineAnalysis().totalOutstandingAmount());

            // Empty collections
            MemberReport kwameReport = reports.get(9);

            assertEquals(0, kwameReport.borrowingAnalysis().totalBooksBorrowed());
            assertEquals(ZERO, kwameReport.fineAnalysis().totalOutstandingAmount());

            // Both collections null
            MemberReport noahReport = reports.get(2);

            assertEquals(0, noahReport.borrowingAnalysis().totalBooksBorrowed());
            assertEquals(ZERO, noahReport.fineAnalysis().totalOutstandingAmount());

            // Borrowed books null, fines populated
            MemberReport meiReport = reports.getFirst();

            assertEquals(0, meiReport.borrowingAnalysis().totalBooksBorrowed());
            assertEquals(new BigDecimal("4.75"), meiReport.fineAnalysis().totalOutstandingAmount());

            // Borrowed books populated, fines null
            MemberReport gabrielReport = reports.getLast();

            assertEquals(2, gabrielReport.borrowingAnalysis().totalBooksBorrowed());
            assertEquals(ZERO, gabrielReport.fineAnalysis().totalOutstandingAmount());
        }
    }
}
