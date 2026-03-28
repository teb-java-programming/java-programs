package com.teb.practice.core;

import java.util.List;

public interface Writer<T> {

    void write(String filePath, List<T> data);
}
