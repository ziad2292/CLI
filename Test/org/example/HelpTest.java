package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelpTest {
    @Test
    void testExecute() {
        Help helpCommand = new Help();
        String result = helpCommand.execute();

        assertTrue(result.contains("Available Commands:"));
        assertTrue(result.contains("exit                          : Terminate the program."));
    }

}