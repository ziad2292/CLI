package org.example;

import java.io.File;
import java.io.IOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RmRecursiveTest {


    private RmRecursive rmRecursiveCommand;

    @Before
    public void setUp() {
        rmRecursiveCommand = new RmRecursive();
    }

    @Test
    public void testDeleteNonEmptyDirectory() {
        try {
            // Create a test directory with files
            File dir = new File("testDir");
            dir.mkdir();
            File file1 = new File("testDir/file1.txt");
            File file2 = new File("testDir/file2.txt");
            file1.createNewFile();
            file2.createNewFile();

            // Set directory path for rmRecursive command

            String result = rmRecursiveCommand.execute("testDir");

            // Verify directory and its contents are deleted
            assertEquals("Directory Deleted Successfully", result);
            assertFalse("Directory should be deleted",dir.exists());
            assertFalse("File 1 should be deleted",file1.exists());
            assertFalse("File 2 should be deleted",file2.exists());

        } catch (IOException e) {
            fail("Exception occurred during test setup or execution: " + e.getMessage());
        }
    }

    @Test
    public void testDeleteNonExistentDirectory() {
        // Set path to a non-existent directory
        rmRecursiveCommand.multiplePaths = new String[]{"nonExistentDir"};
        String result = rmRecursiveCommand.execute("");

        // Expect error message for non-existent directory
        assertEquals("Error Occured while deletion", result);
    }

    @Test
    public void testDeleteFileInsteadOfDirectory() {
        try {
            // Create a test file
            File file = new File("testFile.txt");
            file.createNewFile();

            // Set file path instead of directory
            rmRecursiveCommand.multiplePaths = new String[]{"testFile.txt"};
            String result = rmRecursiveCommand.execute("testFile.txt");

            // Expect the file to be deleted successfully
            assertEquals("Directory Deleted Successfully", result);
            assertFalse("File should be deleted",file.exists());

        } catch (IOException e) {
            fail("Exception occurred during test setup or execution: " + e.getMessage());
        }
    }

    @Test
    public void testDeleteNestedDirectories() {
        try {
            // Create a nested directory structure
            File parentDir = new File("parentDir");
            File childDir = new File("parentDir/childDir");
            parentDir.mkdir();
            childDir.mkdir();
            File fileInChild = new File("parentDir/childDir/file.txt");
            fileInChild.createNewFile();

            // Set path for rmRecursive command

            String result = rmRecursiveCommand.execute("parentDir");

            // Verify parent directory, child directory, and file are deleted
            assertEquals("Directory Deleted Successfully", result);
            assertFalse("Parent directory should be deleted",parentDir.exists());
            assertFalse("Child directory should be deleted",childDir.exists());
            assertFalse("File in child directory should be deleted",fileInChild.exists());

        } catch (IOException e) {
            fail("Exception occurred during test setup or execution: " + e.getMessage());
        }
    }

}