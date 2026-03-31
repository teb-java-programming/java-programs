package com.teb.practice.util.spreadsheet;

import com.teb.practice.core.Reader;
import com.teb.practice.core.Writer;
import com.teb.practice.model.Data;
import com.teb.practice.util.FileUtil;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtil implements Writer<Data>, Reader<Data> {

    private final FileUtil fileUtil = new FileUtil();

    @Override
    public void write(String filePath, List<Data> dataList) {

        int rowIndex = 0;
        fileUtil.resetAndPrepareFile(filePath);

        try (Workbook workbook = createWorkbookWriter()) {
            Sheet sheet = workbook.createSheet("Report");

            Row headerRow = sheet.createRow(rowIndex++);

            headerRow.createCell(0).setCellValue("Name");
            headerRow.createCell(1).setCellValue("Age");
            headerRow.createCell(2).setCellValue("Salary");

            for (Data data : dataList) {
                Row row = sheet.createRow(rowIndex++);

                row.createCell(0).setCellValue(data.name());
                row.createCell(1).setCellValue(data.age());
                row.createCell(2).setCellValue(data.salary());
            }

            try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
                workbook.write(outputStream);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error writing Excel file", e);
        }
    }

    @Override
    public List<Data> read(String filePath) {

        List<Data> result = new ArrayList<>();

        try (FileInputStream inputStream = createInputStream(filePath);
                Workbook workbook = createWorkbookReader(inputStream)) {
            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);

                if (row == null) continue;

                String name = row.getCell(0).getStringCellValue();
                int age = (int) row.getCell(1).getNumericCellValue();
                double salary = row.getCell(2).getNumericCellValue();

                result.add(new Data(name, age, salary));
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading Excel file", e);
        }

        return result;
    }

    protected XSSFWorkbook createWorkbookWriter() {

        return new XSSFWorkbook();
    }

    protected FileInputStream createInputStream(String filePath) throws IOException {

        return new FileInputStream(filePath);
    }

    protected XSSFWorkbook createWorkbookReader(FileInputStream inputStream) throws IOException {

        return new XSSFWorkbook(inputStream);
    }
}
