package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class CatAppendCommand implements CommandInterface {
    public String fileName;

    @Override
    public String execute(String... args) {
        fileName=args[0];

        File file = new File(fileName);
        try (FileWriter writer = new FileWriter(file, true);
             Scanner scanner = new Scanner(System.in))
        {

            if (!file.exists()) {
                file.createNewFile();
                System.out.println("File created: " + fileName);
            }

            System.out.println("Enter content to append to the file (type 'stop' to finish):");

            while (scanner.hasNextLine())
            {
                String content = scanner.nextLine();
                if (content.equalsIgnoreCase("stop")) break;
                writer.write(content + System.lineSeparator());
            }

            System.out.println("Content appended successfully.");
        }
        catch (IOException e) {
            System.out.println("Error appending to file: " + e.getMessage());
        }
        return null;
    }
}
