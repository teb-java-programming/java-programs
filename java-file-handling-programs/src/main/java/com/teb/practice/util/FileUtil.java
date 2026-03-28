package com.teb.practice.util;

import java.io.File;
import java.io.IOException;

public class FileUtil {

    public File resetAndPrepareFile(String filePath) {

        File file = createFile(filePath);
        File parent = file.getParentFile();

        if (parent != null && !parent.exists() && !parent.mkdirs()) {
            throw new RuntimeException("Failed to create directories: " + parent);
        }

        if (file.exists() && !file.delete()) {
            throw new RuntimeException("Failed to delete file: " + filePath);
        }

        try {
            if (!file.createNewFile()) {
                throw new RuntimeException("Failed to create file: " + filePath);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error preparing file: " + filePath, e);
        }

        return file;
    }

    protected File createFile(String filePath) {

        return new File(filePath);
    }
}
