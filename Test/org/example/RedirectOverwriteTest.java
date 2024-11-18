package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

class RedirectOverwriteTest {

    private RedirectOverwrite redirectOverwrite;
    private File file;

    @BeforeEach
    public void setUp() {
        redirectOverwrite = new RedirectOverwrite();
        file = new File("temp.txt");
    }

    @Test
    public void testOverwriteOutputToFile() throws IOException {
        redirectOverwrite.redirectOutput("Hello World", file.getName());

        String content = Files.readString(file.toPath()).trim();
        assertEquals("Hello World", content);
    }

    @Test
    public void testOverwriteWithCatCommand() throws IOException
    {
        redirectOverwrite.redirectOutput("cat", "temp.txt");

        String content = Files.readString(file.toPath());
        assertFalse(content.contains("Initial Content"));
    }

    @AfterEach
    public void tearDown() throws IOException {
        if (file.exists()) {
            Files.delete(file.toPath());
        }
    }

    @Test
    public void testNoFileArgument()
    {
        assertDoesNotThrow(() -> redirectOverwrite.redirectOutput("Hello", null));
    }

    @Test
    public void testDirectoryAsFile() throws IOException
    {
        File tempDir = Files.createTempDirectory("testDir").toFile();
        tempDir.deleteOnExit();

        assertDoesNotThrow(() -> redirectOverwrite.redirectOutput("Hello", tempDir.getPath()));
    }

    @Test
    public void testNoCommandOutput() throws IOException
    {
        File tempFile = File.createTempFile("testFile", ".txt");
        tempFile.deleteOnExit();

        redirectOverwrite.redirectOutput("", tempFile.getName());
        String content = Files.readString(tempFile.toPath());
        assertTrue(content.isEmpty());
    }
}
