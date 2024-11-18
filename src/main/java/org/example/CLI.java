package org.example;
import java.util.ArrayList;
import java.lang.StringBuilder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CLI {
    private final Map<String, CommandInterface> commandMap;

    public CLI() {
        commandMap = new HashMap<>();
        registerCommands();
    }

    private void registerCommands()
    {
        // Register each command with its name and corresponding Command instance
        commandMap.put("ls", new LsHandler());
        commandMap.put("cd", new CdCommand());
        commandMap.put("pwd", new PwdCommand());
        commandMap.put("help", new Help());
        commandMap.put("exit", new ExitCommand());
        commandMap.put("mkdir", new MkdirCommand());
        commandMap.put("rmdir", new RmdirCommand());
        commandMap.put("mv", new MvCommand());
        commandMap.put("touch", new TouchCommand());
        commandMap.put("rm",new RmCommand());
        commandMap.put("cat >", new CatOverwriteCommand() );
        commandMap.put("cat >>", new CatAppendCommand() );
        commandMap.put("cat", new CatCommand());
        commandMap.put("rm -R",new RmRecursive());

        // Add additional commands as needed
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("> "); // Command prompt
            String input = scanner.nextLine();

            if (input.trim().isEmpty())
            {
                continue; // Skip if the input is empty
            }

            if (input.contains("|"))
            {
                handlePipedCommands(input);
            }

            if (input.contains("cat >>") || input.contains("cat >"))
            {
                    if (input.contains("cat >>")) {
                    String[] strA = input.split("cat >>");
                    CatAppendCommand append = new CatAppendCommand();
                    append.execute(strA[1]);
                } else {
                    String[] strO = input.split("cat >");
                    CatOverwriteCommand overwrite = new CatOverwriteCommand();
                    overwrite.execute(strO[1]);
                }
                continue;
            }

            else if (input.contains(">>") || input.contains(">"))
            {
                    String[] parts = input.split(" ");

                    List<String> parsedParts = new ArrayList<>();
                    StringBuilder currentPart = new StringBuilder();

                    for (String part : parts) {
                        if (part.equals(">>") || part.equals(">")) {
                            // Add current part as argument (e.g., Asmaa.txt) before >> if it exists
                            if (currentPart.length() > 0) {
                                parsedParts.add(currentPart.toString());
                                currentPart.setLength(0); // Clear currentPart for next input
                            }
                        } else {
                            if (currentPart.length() > 0) {
                                currentPart.append(" ");
                            }
                            currentPart.append(part);
                        }

                        //"cat Asmaa.txt >> file1"
                    }

                    String cmdName = parts[0];
                    CommandInterface command = commandMap.get(cmdName);
                    // ["cat Asmaa.txt",  "file1"];

                    String[] cmdArgs = parsedParts.toArray(new String[0]);

                    System.out.println(cmdName);

                    for (String i : parsedParts) {
                        System.out.println(i);
                    }


                    if (input.contains(">>")) {
                        RedirectAppend append = new RedirectAppend();
                        append.redirectOutput(command.execute(cmdArgs), currentPart.toString());
                    } else {
                        RedirectOverwrite overwrite = new RedirectOverwrite();
                        overwrite.redirectOutput(command.execute(cmdArgs), currentPart.toString());
                    }
            }

            else{
                    handleSingleCommand(input);
                }

            }
        }


    private void handlePipedCommands(String input) {
        // Split the input by | to handle each command in the pipeline
        String[] commands = input.split("\\|");
        PipeCommand pipeCommand = new PipeCommand();

        for (String cmd : commands) {
            String[] parts = cmd.trim().split(" ", 2);
            String cmdName = parts[0];
            String cmdArgs = parts.length > 1 ? parts[1] : "";

            CommandInterface command = commandMap.get(cmdName);
            if (command != null)
            {
                // Wrap the command with the arguments
                pipeCommand.addCommand(new CommandWrapper(command, cmdArgs));
            }

            else {
                System.out.println("Unknown command: " + cmdName);
                return;
            }
        }

        // Execute the piped commands and print the final output
        String finalOutput = pipeCommand.execute("");
        System.out.println(finalOutput);
    }

    private void handleSingleCommand(String input)
    {
        // Parse the command name and arguments
        String[] parts = input.trim().split(" ",2);

        String cmdName = parts[0];
        String cmdArgs =  parts.length > 1 ? parts[1] : "";



        // Look up the command in the command map
        CommandInterface command = commandMap.get(cmdName);
        if (command != null)
        {
            System.out.println(command.execute(cmdArgs));
        }

        else {
            System.out.println("Unknown command: " + cmdName);
        }
    }
}
