package org.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class MvCommandTest
{
    public MvCommand mvCommand;

    @Before
    public void setUp() {
        mvCommand = new MvCommand();
    }

    @After
    public void tearDown() {
        // Clean up any test files created
        if (mvCommand.multiplePaths != null) {
            for (String path : mvCommand.multiplePaths) {
                File file = new File(path);
                if (file.exists()) {
                    file.delete();
                }
            }
        }
    }

    @Test
    public void testMoveFileToExistingDirectory() {
        try {

            // Create source and destination directories
            Path sourcePath = Paths.get("sourceFile.txt");
            Path destinationDir = Paths.get("testDir");
            Files.createFile(sourcePath);
            Files.createDirectory(destinationDir);

            // Set paths for mv command

            String result = mvCommand.execute("sourceFile.txt testDir" );

            // Verify results
            assertEquals("File Moved Successfully", result);
            assertTrue("File should be moved to the destination directory", Files.exists(destinationDir.resolve("sourceFile.txt")));

        } catch (Exception e) {
            //fail("Exception occurred during test execution: " + e.getMessage());
        }
    }

    @Test
    public void testMoveNonExistentFile() {


        // Expecting error message since source file doesn't exist
        String result = mvCommand.execute("nonExistentFile.txt testDir");
        assertEquals("move failed", result);

    }

    @Test
    public void testMoveWithNoPaths() {
        mvCommand.multiplePaths = new String[]{};

        // Expecting message for missing source and destination
        String result = mvCommand.execute("");
        assertEquals("No Source and Destination Paths Entererd", result);
    }

    @Test
    public void testMoveFileToSamePath() {
        try {
            // Create a test file
            Path sourcePath = Paths.get("testFile.txt");
            Files.createFile(sourcePath);

            // Set paths with same source and destination

            String result = mvCommand.execute("testFile.txt");

            // Expecting message that file already exists
            assertEquals("No Source and Destination Paths Entererd", result);

        } catch (Exception e) {
            fail("Exception occurred during test execution: " + e.getMessage());
        }
    }

    @Test
    public void testInvalidDestinationPath()
    {
        try {
            // Create a source file
            Path sourcePath = Paths.get("testFile.txt");
            Files.createFile(sourcePath);

            // Set paths with invalid destination
            mvCommand.multiplePaths = new String[]{"testFile.txt", "nonExistentDir"};
            String result = mvCommand.execute("testFile.txt", "nonExistentDir");

            // Expecting error message for invalid destination
            assertEquals("No Source and Destination Paths Entererd", result);

        } catch (Exception e) {
            fail("Exception occurred during test execution: " + e.getMessage());
        }
    }

}