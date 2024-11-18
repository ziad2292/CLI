package org.example;


import java.util.ArrayList;
import java.util.List;

public class PipeCommand {
    private final List<CommandWrapper> commands;

    public PipeCommand() {
        this.commands = new ArrayList<>();
    }

    public void addCommand(CommandWrapper command) {
        commands.add(command);
    }

    public String execute(String initialInput) {
        String output = initialInput;

        // Iterate through each command in the pipeline
        for (CommandWrapper command : commands) {
            output = command.execute(output);  // Pass the output of the previous command to the next command
        }

        return output;
    }
}