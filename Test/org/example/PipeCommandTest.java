package org.example;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class PipeCommandTest {
    private PipeCommand pipeCommand;

    @Before
    public void setUp() {
        pipeCommand = new PipeCommand();
    }

    @Test
    public void testSingleCommandInPipeline() {
        // Test with a single command in the pipeline
        pipeCommand.addCommand(new CommandWrapper(new MockCommand("Hello"), ""));

        String result = pipeCommand.execute("");
        assertEquals("Hello", result);
    }

    @Test
    public void testTwoCommandsInPipeline() {
        // Test with two commands in the pipeline
        pipeCommand.addCommand(new CommandWrapper(new MockCommand("Hello"), ""));
        pipeCommand.addCommand(new CommandWrapper(new MockCommand("World"), ""));

        String result = pipeCommand.execute("");
        assertEquals("World", result); // Last command output is the final result
    }

    @Test
    public void testPipelineWithInputModification() {
        // Command that modifies input by appending text
        class AppendCommand implements CommandInterface {
            @Override
            public String execute(String... args) {
                return args[0] + " appended";
            }
        }

        pipeCommand.addCommand(new CommandWrapper(new MockCommand("Start"), ""));
        pipeCommand.addCommand(new CommandWrapper(new AppendCommand(), ""));

        String result = pipeCommand.execute("");
        assertEquals("Start appended", result);
    }


    @Test
    public void testEmptyPipeline() {
        // Test with an empty pipeline
        String result = pipeCommand.execute("Initial input");
        assertEquals("Initial input", result); // Should return initial input if no commands are in the pipeline
    }


}
