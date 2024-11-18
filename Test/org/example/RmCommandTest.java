package org.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class RmCommandTest {

    private RmCommand rmCommand;

    @Before
    public void setUp() {
        rmCommand = new RmCommand();
    }

    @After
    public void tearDown() {
        // Clean up any files or directories created during tests
        deleteIfExists("testFile.txt");
        deleteIfExists("testFile1.txt");
        deleteIfExists("testFile2.txt");
        deleteIfExists("nonExistentFile.txt");
        deleteIfExists("testDirectory");
    }

    private void deleteIfExists(String path) {
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void testDeleteExistingFile() {
        try {
            File file = new File("testFile.txt");
            assertTrue("Test file should be created successfully", file.createNewFile());

            rmCommand.multiplePaths = new String[]{"testFile.txt"};
            String result = rmCommand.execute("testFile.txt");

            assertTrue(result.contains("File Deleted: testFile.txt"));
            assertFalse("File should be deleted", file.exists());
        } catch (Exception e) {
            fail("Exception occurred during test setup or execution: " + e.getMessage());
        }
    }

    @Test
    public void testDeleteNonExistentFile() {
        String result = rmCommand.execute("nonExistentFile.txt");
        assertTrue(result.contains("Failed To Delete File: nonExistentFile.txt"));
    }



    @Test
    public void testMultipleFilesDeletion() {
        try {
            File file1 = new File("testFile1.txt");
            File file2 = new File("testFile2.txt");
            assertTrue("Test file 1 should be created", file1.createNewFile());
            assertTrue("Test file 2 should be created", file2.createNewFile());

            rmCommand.multiplePaths = new String[]{"testFile1.txt", "testFile2.txt"};
            String result = rmCommand.execute("testFile1.txt testFile2.txt");

            assertTrue(result.contains("File Deleted: testFile1.txt"));
            assertTrue(result.contains("File Deleted: testFile2.txt"));
            assertFalse("Test file 1 should be deleted", file1.exists());
            assertFalse("Test file 2 should be deleted", file2.exists());
        } catch (Exception e) {
            fail("Exception occurred during test setup or execution: " + e.getMessage());
        }
    }
}
