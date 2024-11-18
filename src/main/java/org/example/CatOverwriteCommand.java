package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class CatOverwriteCommand implements CommandInterface {
    public String fileName;


    @Override
    public String execute(String... args)
    {
        fileName=args[0];
        try (FileWriter writer = new FileWriter(fileName, false);
             Scanner scanner = new Scanner(System.in))
        {
            System.out.println("Enter content to overwrite the file (type 'stop' to finish):");

            while (true)
            {
                String content = scanner.nextLine();
                if (content.equalsIgnoreCase("stop")) break;
                writer.write(content + System.lineSeparator());
            }
            System.out.println("File overwritten successfully.");
        }

        catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
        return null;
    }
}
