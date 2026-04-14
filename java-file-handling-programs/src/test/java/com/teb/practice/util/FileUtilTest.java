package com.teb.practice.util;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

class FileUtilTest {

    private File mockFile;
    private File mockParent;

    @BeforeEach
    void setUp() {

        mockFile = mock(File.class);
        mockParent = mock(File.class);
    }

    @Test
    void testCreatesDirectoriesWhenNoParentExists() throws IOException {

        when(mockFile.getParentFile()).thenReturn(mockParent);
        when(mockParent.exists()).thenReturn(false);
        when(mockParent.mkdirs()).thenReturn(true);
        when(mockFile.exists()).thenReturn(false);
        when(mockFile.createNewFile()).thenReturn(true);

        FileUtil fileUtil = fileUtilWith(mockFile);
        File result = fileUtil.resetAndPrepareFile("test");

        assertNotNull(result);
    }

    @Test
    void testThrowsExceptionWhenDirectoryCreationFails() {

        when(mockFile.getParentFile()).thenReturn(mockParent);
        when(mockParent.exists()).thenReturn(false);
        when(mockParent.mkdirs()).thenReturn(false);

        FileUtil fileUtil = fileUtilWith(mockFile);

        RuntimeException e =
                assertThrows(RuntimeException.class, () -> fileUtil.resetAndPrepareFile("test"));
        assertTrue(e.getMessage().contains("Failed to create directories"));
    }

    @Test
    void testThrowsExceptionWhenNewFileCreationFails() throws IOException {

        when(mockFile.getParentFile()).thenReturn(null);
        when(mockFile.exists()).thenReturn(false);
        when(mockFile.createNewFile()).thenReturn(false);

        FileUtil fileUtil = fileUtilWith(mockFile);

        RuntimeException e =
                assertThrows(RuntimeException.class, () -> fileUtil.resetAndPrepareFile("test"));
        assertTrue(e.getMessage().contains("Failed to create file"));
    }

    @Test
    void testThrowsExceptionWhenFileDeletionFails() {

        when(mockFile.getParentFile()).thenReturn(null);
        when(mockFile.exists()).thenReturn(true);
        when(mockFile.delete()).thenReturn(false);

        FileUtil fileUtil = fileUtilWith(mockFile);

        RuntimeException e =
                assertThrows(RuntimeException.class, () -> fileUtil.resetAndPrepareFile("test"));
        assertTrue(e.getMessage().contains("Failed to delete file"));
    }

    @Test
    void testThrowsWhenIOExceptionOccurs() throws IOException {

        when(mockFile.getParentFile()).thenReturn(null);
        when(mockFile.exists()).thenReturn(false);
        when(mockFile.createNewFile()).thenThrow(new IOException("IO error"));

        FileUtil fileUtil = fileUtilWith(mockFile);

        RuntimeException e =
                assertThrows(RuntimeException.class, () -> fileUtil.resetAndPrepareFile("test"));
        assertTrue(e.getMessage().contains("Error preparing file"));
    }

    private FileUtil fileUtilWith(File mockFile) {

        return new FileUtil() {

            @Override
            protected File createFile(String filePath) {

                return mockFile;
            }
        };
    }
}
