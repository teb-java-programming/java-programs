package com.teb.practice.util.document;

import static org.apache.pdfbox.pdmodel.font.Standard14Fonts.FontName.HELVETICA;

import com.teb.practice.core.Writer;
import com.teb.practice.model.Data;
import com.teb.practice.util.FileUtil;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;
import java.util.List;

public class PdfUtil implements Writer<Data> {

    private final FileUtil fileUtil = new FileUtil();

    @Override
    public void write(String filePath, List<Data> dataList) {

        fileUtil.resetAndPrepareFile(filePath);

        try (PDDocument pdDocument = createDocument()) {
            PDPage pdPage = new PDPage();
            pdDocument.addPage(pdPage);

            try (PDPageContentStream pdPageContentStream =
                    new PDPageContentStream(pdDocument, pdPage)) {
                pdPageContentStream.beginText();
                pdPageContentStream.setFont(new PDType1Font(HELVETICA), 12);
                pdPageContentStream.setLeading(16.0f);
                pdPageContentStream.newLineAtOffset(50, 700);

                pdPageContentStream.showText("Data Report");
                pdPageContentStream.newLine();
                pdPageContentStream.newLine();

                for (Data data : dataList) {
                    pdPageContentStream.showText(
                            data.name() + "  |  " + data.age() + "  |  " + data.salary());
                    pdPageContentStream.newLine();
                }

                pdPageContentStream.endText();
            }

            pdDocument.save(filePath);
        } catch (IOException e) {
            throw new RuntimeException("Error writing PDF file", e);
        }
    }

    protected PDDocument createDocument() {

        return new PDDocument();
    }
}
