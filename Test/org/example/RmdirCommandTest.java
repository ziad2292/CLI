package org.example;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.example.RmdirCommand;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RmdirCommandTest {
    private RmdirCommand rmdirCommand;
    private Path testDir;
    private Path nonEmptyDir;

    @Before
    public void setUp() throws IOException {
        rmdirCommand = new RmdirCommand();

        testDir = Paths.get("testDirToDelete");
        Files.createDirectories(testDir);

        nonEmptyDir = Paths.get("nonEmptyDirToDelete");
        Files.createDirectories(nonEmptyDir);
        Files.createFile(nonEmptyDir.resolve("file.txt"));
    }

    @Test
    public void testExecuteRemovesDirectory() {

        rmdirCommand.execute(testDir.toString());

        assertFalse("Directory should be deleted", Files.exists(testDir));
    }

    @Test
    public void testExecuteNonExistentDirectory() {

        rmdirCommand.execute("nonExistentDir");

        assertFalse("Directory should not exist", Files.exists(Paths.get("nonExistentDir")));
    }

    @Test
    public void testExecuteNonEmptyDirectory() {

        rmdirCommand.execute(nonEmptyDir.toString());

        assertTrue("Non-empty directory should not be deleted", Files.exists(nonEmptyDir));
    }

    @After
    public void tearDown() throws IOException {
        if (Files.exists(testDir)) {
            Files.delete(testDir);
        }
        if (Files.exists(nonEmptyDir)) {
            Files.walk(nonEmptyDir)
                    .map(Path::toFile)
                    .forEach(File::delete);
        }
    }
}