package com.teb.practice.util.document;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.spy;

import com.teb.practice.model.Data;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

class WordUtilTest {

    private static final String FILE_PATH = "src/test/resources/output/word-test.docx";

    private WordUtil wordUtil = new WordUtil();

    @Test
    void testCreatesAndWritesWordFile() {

        List<Data> input = List.of(new Data("John", 48, 52000), new Data("Allen", 48, 36000));
        File file = new File(FILE_PATH);

        wordUtil.write(FILE_PATH, input);

        assertTrue(file.exists());
        assertTrue(file.length() > 0);
    }

    @Test
    void testThrowsExceptionWhenWriteFails() throws Exception {

        wordUtil = spy(new WordUtil());
        XWPFDocument xwpfDocument = spy(new XWPFDocument());

        doReturn(xwpfDocument).when(wordUtil).createDocument();
        doThrow(new IOException("Write failed")).when(xwpfDocument).write(any());

        Exception e =
                assertThrows(
                        RuntimeException.class,
                        () -> wordUtil.write("target/test/test.docx", List.of()));
        assertTrue(e.getMessage().contains("Error writing Word file"));
        assertInstanceOf(IOException.class, e.getCause());
    }
}
