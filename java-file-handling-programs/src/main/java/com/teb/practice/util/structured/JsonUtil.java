package com.teb.practice.util.structured;

import com.teb.practice.core.Reader;
import com.teb.practice.core.Writer;
import com.teb.practice.model.Data;
import com.teb.practice.model.DataList;
import com.teb.practice.util.FileUtil;

import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.List;

public class JsonUtil implements Writer<Data>, Reader<Data> {

    private final FileUtil fileUtil = new FileUtil();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void write(String filePath, List<Data> dataList) {

        objectMapper
                .writerWithDefaultPrettyPrinter()
                .writeValue(fileUtil.resetAndPrepareFile(filePath), new DataList(dataList));
    }

    @Override
    public List<Data> read(String filePath) {

        return objectMapper.readValue(new File(filePath), DataList.class).data();
    }
}
