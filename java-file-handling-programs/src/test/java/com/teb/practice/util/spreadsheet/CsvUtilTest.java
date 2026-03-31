package com.teb.practice.util.spreadsheet;

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

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.teb.practice.model.Data;

import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

class CsvUtilTest {

    private static final String FILE_PATH = "src/test/resources/output/csv-test.csv";

    private CsvUtil csvUtil = new CsvUtil();

    @Test
    void testWritesAndReadsCsvFile() {

        List<Data> input = List.of(new Data("Steven", 57, 72000), new Data("Richard", 57, 40000));

        csvUtil.write(FILE_PATH, input);

        List<Data> output = csvUtil.read(FILE_PATH);

        assertEquals(input, output);
    }

    @Test
    void testThrowsExceptionWhenWriteFails() throws Exception {

        csvUtil = spy(new CsvUtil());

        doThrow(new IOException("Write failed")).when(csvUtil).createFileWriter(anyString());

        Exception e =
                assertThrows(
                        RuntimeException.class,
                        () -> csvUtil.write("target/test/test.csv", List.of()));
        assertTrue(e.getMessage().contains("Error writing CSV file"));
        assertInstanceOf(IOException.class, e.getCause());
    }

    @Test
    void testThrowsIOExceptionWhenReadFails() throws Exception {

        csvUtil = spy(new CsvUtil());

        doThrow(new IOException("Read failed")).when(csvUtil).createFileReader(anyString());

        Exception e =
                assertThrows(RuntimeException.class, () -> csvUtil.read("target/test/test.csv"));
        assertTrue(e.getMessage().contains("Error reading CSV file"));
        assertInstanceOf(IOException.class, e.getCause());
    }

    @Test
    void testThrowsCsvExceptionWhenReadFails() throws Exception {

        csvUtil = spy(new CsvUtil());
        FileReader fileReader = mock(FileReader.class);
        CSVReader csvReader = mock(CSVReader.class);

        doReturn(fileReader).when(csvUtil).createFileReader(anyString());
        doReturn(csvReader).when(csvUtil).createCsvReader(any());
        doThrow(new CsvException("CSV failed")).when(csvReader).readAll();

        Exception e =
                assertThrows(RuntimeException.class, () -> csvUtil.read("target/test/test.csv"));
        assertTrue(e.getMessage().contains("Error reading CSV file"));
        assertInstanceOf(CsvException.class, e.getCause());
    }
}
