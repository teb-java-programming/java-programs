package com.teb.practice.util.document;

import com.teb.practice.core.Writer;
import com.teb.practice.model.Data;
import com.teb.practice.util.FileUtil;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class WordUtil implements Writer<Data> {

    private final FileUtil fileUtil = new FileUtil();

    @Override
    public void write(String filePath, List<Data> dataList) {

        fileUtil.resetAndPrepareFile(filePath);

        try (XWPFDocument xwpfDocument = createDocument()) {
            XWPFParagraph xwpfParagraph = xwpfDocument.createParagraph();
            xwpfParagraph.createRun().setText("Data Report");

            for (Data data : dataList) {
                xwpfParagraph
                        .createRun()
                        .setText(data.name() + "  |  " + data.age() + "  |  " + data.salary());
            }

            try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
                xwpfDocument.write(outputStream);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error writing Word file", e);
        }
    }

    protected XWPFDocument createDocument() {

        return new XWPFDocument();
    }
}
