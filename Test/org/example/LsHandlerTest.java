package org.example;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class LsHandlerTest {
    private LsHandler lsHandler;
    private File testDir;
    private String originalDir;

    @BeforeEach
    public void setUp() throws Exception {
        // Save the original working directory
        originalDir = System.getProperty("user.dir");

        // Create and set up a temporary test directory
        testDir = new File("testDir");
        if (!testDir.exists()) {
            testDir.mkdir();
        }

        createFile("testDir/file1.txt");
        createFile("testDir/file2.txt");
        File subDir = new File("testDir/subDir");
        subDir.mkdir();
        createFile("testDir/subDir/file3.txt");

        // Change the working directory to the test directory
        System.setProperty("user.dir", testDir.getAbsolutePath());

        // Initialize LsHandler
        lsHandler = new LsHandler();
    }

    @Test
    public void testExecuteDefaultCommand() {
        String result = lsHandler.execute("");
        assertTrue(result.contains("file1.txt"));
        assertTrue(result.contains("file2.txt"));
        assertTrue(result.contains("subDir/"));
    }

    @Test
    public void testExecuteLsAllCommand() {
        String result = lsHandler.execute("-a");
        assertTrue(result.contains("file1.txt"));
        assertTrue(result.contains("file2.txt"));
        assertTrue(result.contains("subDir/"));
    }



    @Test
    public void testExecuteUnrecognizedCommand() {
        String result = lsHandler.execute("-invalid");
        assertEquals("Unrecognized command. Try 'ls', 'ls -a', 'ls -R', or 'ls -a -R'.", result);
    }

    @AfterEach
    public void tearDown() throws Exception {
        // Restore the original working directory
        System.setProperty("user.dir", originalDir);

        // Clean up the test directory
        deleteDirectory(testDir);
    }

    private void createFile(String path) throws IOException {
        File file = new File(path);
        file.createNewFile();
    }

    private void deleteDirectory(File dir) {
        if (dir.isDirectory()) {
            for (File file : dir.listFiles()) {
                deleteDirectory(file);
            }
        }
        dir.delete();
    }
}

/*



 */