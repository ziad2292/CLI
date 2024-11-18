package org.example;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.io.File;
import org.example.CdCommand;


public class CdCommandTest {

    private CdCommand cdCommand;
    private String initialDir;

    @Before
    public void setUp() {
        cdCommand = new CdCommand();
        initialDir = System.getProperty("user.dir");
    }

    @Test
    public void testSetPathAndExecuteWithRelativePath() {
        String relativePath = "src";

        cdCommand.execute(relativePath);

        String expectedPath = new File(initialDir, relativePath).getAbsolutePath();
        String currentDir = System.getProperty("user.dir");

        assertEquals("Failed to navigate to the relative path", expectedPath, currentDir);
    }

    @Test
    public void testSetPathAndExecuteWithParentDirectory() {

        cdCommand.execute("..");

        File parentDir = new File(initialDir).getParentFile();
        String expectedPath = parentDir != null ? parentDir.getAbsolutePath() : initialDir;
        String currentDir = System.getProperty("user.dir");

        assertEquals("Failed to navigate to the parent directory", expectedPath, currentDir);
    }

    @Test
    public void testSetPathAndExecuteWithNonExistentDirectory() {

        cdCommand.execute("nonexistentdirectory");

        String currentDir = System.getProperty("user.dir");

        assertEquals("Current directory should not change for a nonexistent path", initialDir, currentDir);
    }

    @Test
    public void testSetPathAndExecuteWithAbsolutePath() {
        String absolutePath = initialDir;

        cdCommand.execute(absolutePath);

        String currentDir = System.getProperty("user.dir");

        assertEquals("Failed to navigate to the absolute path", absolutePath, currentDir);
    }
    @After
    public void tearDown() {
        System.setProperty("user.dir", initialDir);
    }
}