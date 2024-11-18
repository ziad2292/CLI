package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CatCommandTest
{
    private CatCommand catCommand;

    @BeforeEach
    public void setUp() {
        catCommand = new CatCommand();
    }

    @Test
    public void testCatSingleFile() throws IOException
    {
        // create temp file and write content to iy
        File file = new File("temp.txt");

        FileWriter writer = new FileWriter("temp.txt");
        writer.write("Hii\n");
        writer.write("this is a test file\n");
        writer.close();

        String[] args = {"temp.txt"};
        String result = catCommand.execute(args);
        assertEquals("Hii\nthis is a test file\n", result);
    }

    @Test
    public void testCatFileNotFound()
    {
        String[] args = {"nonexistentfile.txt"};
        String result = catCommand.execute(args);
        assertEquals("cat: nonexistentfile.txt: No such file or directory\n", result);
    }

    @Test
    public void testCatNoArguments()
    {
        String[] args = {""};
        String result = catCommand.execute(args);
        assertEquals("Please specify at least one file. \n", result);
    }

    @Test
    public void testCatDirectoryAsFile() throws IOException
    {
        File tempDir = new File(System.getProperty("java.io.tmpdir"), "testDir");
        tempDir.mkdir();
        tempDir.deleteOnExit();

        String[] args = {tempDir.getName()};
        String result = catCommand.execute(args);
        assertEquals("cat: " + tempDir.getName() + ": No such file or directory\n", result);
    }
}