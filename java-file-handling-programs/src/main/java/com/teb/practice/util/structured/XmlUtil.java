package com.teb.practice.util.structured;

import com.teb.practice.core.Reader;
import com.teb.practice.core.Writer;
import com.teb.practice.model.Data;
import com.teb.practice.model.DataList;
import com.teb.practice.util.FileUtil;

import tools.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.util.List;

public class XmlUtil implements Writer<Data>, Reader<Data> {

    private final FileUtil fileUtil = new FileUtil();
    private final XmlMapper xmlMapper = XmlMapper.builder().defaultUseWrapper(false).build();

    @Override
    public void write(String filePath, List<Data> dataList) {

        xmlMapper
                .writerWithDefaultPrettyPrinter()
                .writeValue(fileUtil.resetAndPrepareFile(filePath), new DataList(dataList));
    }

    @Override
    public List<Data> read(String filePath) {

        return xmlMapper.readValue(new File(filePath), DataList.class).data();
    }
}
