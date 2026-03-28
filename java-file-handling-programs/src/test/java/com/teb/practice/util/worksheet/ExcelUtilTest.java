package com.teb.practice.util.worksheet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

import com.teb.practice.model.Data;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

class ExcelUtilTest {

    private static final String FILE_PATH = "src/test/resources/output/excel-test.xlsx";

    private ExcelUtil excelUtil = new ExcelUtil();

    @Test
    void testWritesAndReadsExcelFile() {

        List<Data> input = List.of(new Data("Mark", 55, 48000), new Data("Michael", 46, 56000));

        excelUtil.write(FILE_PATH, input);

        List<Data> output = excelUtil.read(FILE_PATH);

        assertEquals(input.size(), output.size());
        assertEquals(input, output);
    }

    @Test
    void testThrowsExceptionWhenWriteFails() throws Exception {

        excelUtil = spy(new ExcelUtil());
        Workbook workbook = spy(new XSSFWorkbook());

        doReturn(workbook).when(excelUtil).createWorkbookWriter();
        doThrow(new IOException("Write failed")).when(workbook).write(any());

        Exception e =
                assertThrows(
                        RuntimeException.class,
                        () -> excelUtil.write("target/test/test.xlsx", List.of()));
        assertTrue(e.getMessage().contains("Error writing Excel file"));
        assertInstanceOf(IOException.class, e.getCause());
    }

    @Test
    void testThrowsIOExceptionWhenReadFails() throws Exception {

        excelUtil = spy(new ExcelUtil());
        FileInputStream inputStream = mock(FileInputStream.class);

        doReturn(inputStream).when(excelUtil).createInputStream(anyString());
        doThrow(new IOException("Read failed"))
                .when(excelUtil)
                .createWorkbookReader(any(FileInputStream.class));

        Exception e =
                assertThrows(RuntimeException.class, () -> excelUtil.read("target/test/test.xlsx"));
        assertTrue(e.getMessage().contains("Error reading Excel file"));
        assertInstanceOf(IOException.class, e.getCause());
    }

    @Test
    void testNullRowsAreSkippedWhenReading() throws Exception {

        excelUtil = spy(new ExcelUtil());
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();

        sheet.createRow(0);

        Row row = sheet.createRow(2);
        row.createCell(0).setCellValue("Kurt");
        row.createCell(1).setCellValue(50);
        row.createCell(2).setCellValue(60000);

        doReturn(workbook).when(excelUtil).createWorkbookReader(any());
        doReturn(mock(FileInputStream.class)).when(excelUtil).createInputStream(anyString());

        List<Data> result = excelUtil.read("target/test/test.xlsx");

        assertEquals(1, result.size());
        assertEquals("Kurt", result.getFirst().name());
    }
}
