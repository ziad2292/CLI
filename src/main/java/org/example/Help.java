package org.example;


public class Help implements CommandInterface
{
    @Override
    public String execute(String... args)
    {
        String str = 
        "Available Commands:\n" +
        "cat > [file]                  : Creates a file if not exists and redirects the input that user types to it, overwrites if file exists.\n" +
        "cat >> [file]                 : Creates a file if not exists and redirects the input that user types to it, appends if file exists.\n" +
        "exit                          : Terminate the program.\n" +
        "help                          : Show this help message.\n" +
        "ls                            : List files and directories in the current directory.\n" +
        "ls -a                         : List all files, including hidden files in the current directory.\n" +
        "ls -r                         : List files in reverse order.\n" +
        "pwd                           : Print the current working directory.\n" +
        "cd [directory]                : Change the current directory.\n" +
        "mkdir [directory]             : Create a new directory.\n" +
        "rmdir [directory]             : Remove an empty directory.\n" +
        "rm [file]                     : Remove a file.\n" +
        "rm -r [directory]             : Remove files recursively from directory.\n" +
        "mv [source] [destination]     : Move or rename a file or directory.\n" +
        "| (Pipe)                      : Redirect output of one command to another.\n" +
        "cat [file1 file2 ...]         : Display content of specified files.\n";

        return str;
    }
}
