package org.example;

import java.io.File;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TouchCommandTest {
    private TouchCommand touchCommand;

    @Before
    public void setUp() {
        touchCommand = new TouchCommand();
    }

    @Test
    public void testCreateNewFile() {
        // Test creating a new file
        touchCommand.multiplePaths = new String[]{"testFile.txt"};
        String result = touchCommand.execute("testFile.txt");
        assertEquals("File Created Successfully", result);

        File file = new File("testFile.txt");
        assertTrue("File should be created", file.exists());
    }

    @Test
    public void testUpdateExistingFile() {
        // Pre-create the file
        File existingFile = new File("existingFile.txt");
        try {
            // Ensure the file is created
            existingFile.createNewFile();
            long initialLastModified = existingFile.lastModified();

            // Wait to ensure timestamp difference
            Thread.sleep(1000);

            // Execute the touch command to update the timestamp
            String result = touchCommand.execute("existingFile.txt");
            assertEquals("File Timestamp Updated", result);

            // Verify the timestamp was updated
            assertTrue("File timestamp should be updated", existingFile.lastModified() > initialLastModified);

        } catch (Exception e) {
            fail("Exception thrown during test setup or execution: " + e.getMessage());
        }
    }

    @Test
    public void testFileCreationException() {
        // Set an invalid path to trigger an exception
        String result = touchCommand.execute("invalidPath/illegalFile.txt");
        assertTrue("An exception should be caught and returned", result.contains("Exception"));
    }

    @After
    public void tearDown() {
        // Clean up any test files created
        deleteFileIfExists("testFile.txt");
        deleteFileIfExists("existingFile.txt");
    }

    private void deleteFileIfExists(String fileName) {
        File file = new File(fileName);
        if (file.exists()) {
            file.delete();
        }
    }
}
