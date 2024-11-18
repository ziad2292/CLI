package org.example;


public class LsHandler implements CommandInterface{
    @Override
    public String execute(String... args)
    {
        String flag = (args.length > 0) ? args[0] : "";
        LsInterface command = getLsCommand(flag);
        if (command != null) {
            return command.execute();
        } else {
            return "Unrecognized command. Try 'ls', 'ls -a', 'ls -R', or 'ls -a -R'.";
        }
    }


    public LsInterface getLsCommand(String input)
    {
        return switch (input) {
            case "" -> new LsCommand();
            case "-a" -> new LsAllCommand();
            case "-R" -> new LsRecursiveCommand();
            case "-r" -> new LsReverseCommand();
            default -> null;
        };
    }




}