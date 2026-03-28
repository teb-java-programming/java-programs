package com.teb.practice.util.worksheet;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import com.teb.practice.core.Reader;
import com.teb.practice.core.Writer;
import com.teb.practice.model.Data;
import com.teb.practice.util.FileUtil;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvUtil implements Writer<Data>, Reader<Data> {

    private final FileUtil fileUtil = new FileUtil();

    @Override
    public void write(String filePath, List<Data> dataList) {

        fileUtil.resetAndPrepareFile(filePath);

        try (CSVWriter csvWriter = new CSVWriter(createFileWriter(filePath))) {
            csvWriter.writeNext(new String[] {"Name", "Age", "Salary"});

            for (Data data : dataList) {
                csvWriter.writeNext(
                        new String[] {data.name(), valueOf(data.age()), valueOf(data.salary())});
            }
        } catch (IOException e) {
            throw new RuntimeException("Error writing CSV file", e);
        }
    }

    @Override
    public List<Data> read(String filePath) {

        List<Data> result = new ArrayList<>();

        try (CSVReader csvReader = createCsvReader(createFileReader(filePath))) {
            List<String[]> rows = csvReader.readAll();

            for (int i = 1; i < rows.size(); i++) {
                String[] row = rows.get(i);
                String name = row[0];
                int age = parseInt(row[1]);
                double salary = parseDouble(row[2]);

                result.add(new Data(name, age, salary));
            }
        } catch (IOException | CsvException e) {
            throw new RuntimeException("Error reading CSV file", e);
        }

        return result;
    }

    protected FileWriter createFileWriter(String filePath) throws IOException {

        return new FileWriter(filePath);
    }

    protected CSVReader createCsvReader(FileReader reader) {

        return new CSVReader(reader);
    }

    protected FileReader createFileReader(String filePath) throws IOException {

        return new FileReader(filePath);
    }
}
