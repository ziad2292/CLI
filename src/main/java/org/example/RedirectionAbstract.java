package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public abstract class RedirectionAbstract
{

    //to handle case: no argument specified
    public final void NoFileArgumentCase()
    {
        System.out.println("Syntax Error: File name is required");
        return;
    }

    //to handle case: no file
    public final void NotFileCase(String fileName)
    {
        System.out.println(fileName+ " is a directory, a file is required");
        return;
    }


    // to handle case: >>file or >file
    public final void NoCommandOutputCase(File file)
    {
        if (!file.exists())
        {
            System.out.println("File " + file.getName() + " does not exist. Creating a new file...");
            try (FileWriter writer = new FileWriter(file))
            {
                // Creating an empty file
            }

            catch (IOException e)
            {
                System.out.println("Error creating file: " + file.getName() + ", " + e.getMessage());
            }
        }

        else
        {
            System.out.println("File " + file.getName() + " already exists, no operation performed");
        }

    }
}

