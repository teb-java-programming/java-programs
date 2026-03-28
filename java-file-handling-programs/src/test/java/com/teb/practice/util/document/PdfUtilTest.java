package com.teb.practice.util.document;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.spy;

import com.teb.practice.model.Data;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

class PdfUtilTest {

    private static final String FILE_PATH = "src/test/resources/output/pdf-test.pdf";

    private PdfUtil pdfUtil = new PdfUtil();

    @Test
    void testCreatesAndWritesPdfFile() {

        List<Data> input = List.of(new Data("Michael", 44, 64000), new Data("Roderick", 57, 48000));
        File file = new File(FILE_PATH);

        pdfUtil.write(FILE_PATH, input);

        assertTrue(file.exists());
        assertTrue(file.length() > 0);
    }

    @Test
    void testThrowsExceptionWhenSaveFails() throws Exception {

        pdfUtil = spy(new PdfUtil());
        PDDocument pdDocument = spy(new PDDocument());

        doReturn(pdDocument).when(pdfUtil).createDocument();
        doThrow(new IOException("Save failed")).when(pdDocument).save(anyString());

        Exception e =
                assertThrows(
                        RuntimeException.class,
                        () -> pdfUtil.write("target/test/test.pdf", List.of()));
        assertTrue(e.getMessage().contains("Error writing PDF file"));
        assertInstanceOf(IOException.class, e.getCause());
    }
}
