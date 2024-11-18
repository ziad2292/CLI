package org.example;

import static org.junit.Assert.assertTrue;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.example.MkdirCommand;

import java.io.File;

public class MkdirCommandTest {
    private MkdirCommand mkdirCommand;
    private String[] testPaths;

    @Before
    public void setUp() {
        mkdirCommand = new MkdirCommand();
        // Define test paths as separate entries
        testPaths = new String[] { "testDir1 testDir2" };
    }

    @Test
    public void testExecuteCreatesDirectories() {
        // Execute mkdirCommand with test paths
        mkdirCommand.execute(testPaths);
        String[] pr = testPaths[0].split(" ");


        // Check that each directory in testPaths has been created
        for (String path : pr) {
            File dir = new File(path);
            assertTrue("Directory " + path + " should be created", dir.exists() && dir.isDirectory());
        }
    }

    @After
    public void tearDown() {
        // Delete all directories created during the test
        for (String path : testPaths) {
            File dir = new File(path);
            deleteDirectory(dir);
        }
    }

    private void deleteDirectory(File file) {
        if (file.isDirectory()) {
            for (File subFile : file.listFiles()) {
                deleteDirectory(subFile);
            }
        }
        file.delete();
    }
}
