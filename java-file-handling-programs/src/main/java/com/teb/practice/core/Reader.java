package com.teb.practice.core;

import java.util.List;

public interface Reader<T> {

    List<T> read(String filePath);
}
