package com.teb.practice.util.structured;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.teb.practice.model.Data;

import org.junit.jupiter.api.Test;

import java.util.List;

class XmlUtilTest {

    private static final String FILE_PATH = "src/test/resources/output/xml-test.xml";

    private final XmlUtil xmlUtil = new XmlUtil();

    @Test
    void testWritesAndReadsXmlFile() {

        List<Data> input = List.of(new Data("Jeffrey", 48, 48000), new Data("Matthew", 51, 40000));

        xmlUtil.write(FILE_PATH, input);

        List<Data> output = xmlUtil.read(FILE_PATH);

        assertEquals(input, output);
    }
}
