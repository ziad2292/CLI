package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;


public class CatCommand implements CommandInterface
{
    @Override
    public String execute(String[] args)
    {

        String[] pr = args[0].split(" ");
        // case no arguments specified
        if (Objects.equals(args[0], "") || Objects.equals(args[0], "\n"))
        {
            return "Please specify at least one file. \n";
        }

        File baseDir = new File(System.getProperty("user.dir"));
        StringBuilder result = new StringBuilder();


        for (String fileName : pr)
        {
            File file = new File(baseDir,fileName);
            System.out.println(fileName);


            // case no file not found

            if (file.isDirectory())
            {
                result.append("cat: ").append(fileName).append(": is a directory, not a file\n");
                continue;
                // Move to the next file
            }

            else
            {
                // trying to read the file content
                try (Scanner scanner = new Scanner(file))
                {
                    while (scanner.hasNextLine())
                    {
                        result.append(scanner.nextLine()).append("\n");
                    }
                }
                catch (FileNotFoundException e)
                {
                    result.append("cat: ").append(fileName).append(": Unable to read the file\n");
                }
            }
        }
        return result.toString();
    }
}