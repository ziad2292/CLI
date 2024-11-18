package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class RedirectOverwrite extends RedirectionAbstract implements RedirectionInterface 
{
    @Override
    public void redirectOutput(String commandOutput, String fileName) 
    { 
        //case: no argument specified
        if (fileName == null || fileName.trim().isEmpty()) 
        {
            NoFileArgumentCase();
        }

        else 
        {
            File file = new File(fileName);
            if (!file.isFile())
            {
                NotFileCase(fileName);
            }
           
//            else if (commandOutput.equals("cat"))
//            {
//                System.out.println("Enter text to be written to the file: " + fileName + " (type 'stop' to finish): ");
//
//                //the following will automatically overwrite if the file exists or creates it in case it does not exist
//                try (Scanner scanner = new Scanner(System.in); FileWriter writer = new FileWriter(file,false))
//                {
//                    String line;
//                    while (!(line = scanner.nextLine()).equalsIgnoreCase("stop"))
//                    {
//                        writer.write(line + System.lineSeparator());
//                        writer.flush();
//                    }
//                }
//
//                catch (IOException e)
//                {
//                    System.out.println("Error writing to file: " + fileName + ", " + e.getMessage());
//                }
//            }

        
            // case: > file, handle it separately
            if (commandOutput == null || commandOutput.trim().isEmpty()) 
            {
                NoCommandOutputCase(file);
                return;
            } 
            
            // dafault case: command > file
            else 
            { 
                try (FileWriter writer = new FileWriter(file, false)) 
                {
                    writer.write(commandOutput);
                    System.out.println("Output written to file: " + fileName);
                } 
                
                catch (IOException e) 
                {
                    System.out.println("Error writing to file: " + fileName);
                }
            }
        }
     } 
}