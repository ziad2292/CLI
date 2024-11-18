package org.example;

public class CommandWrapper implements CommandInterface
{
    private final CommandInterface command;
    private final String args;

    public CommandWrapper(CommandInterface command, String args) {
        this.command = command;
        this.args = args;
    }

    @Override
    public String execute(String... input) {
        // Use args if provided; otherwise, use the input from the previous command in the pipeline
        String finalArgs = args.isEmpty() ? input[0] : args;
        return command.execute(finalArgs);
    }
}