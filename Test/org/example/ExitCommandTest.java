package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ExitCommandTest
{
    @Test
    void testExecute()
    {
        ExitCommand exitCommand = new ExitCommand();
        assertEquals("Exiting...", exitCommand.execute());
    }
}