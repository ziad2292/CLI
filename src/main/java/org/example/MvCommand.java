package org.example;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MvCommand extends CommandAbstract{

    @Override
    public String execute(String... args){


        getMultiplePaths(args[0]);
        int numberOfPaths = multiplePaths.length;

        if(numberOfPaths < 2) return "No Source and Destination Paths Entererd";

        //Create on Destination Object for multiple move operations
        String dest = multiplePaths[numberOfPaths-1];
        File destination = new File(dest);
        Path destinPath = Paths.get(dest);

        if(numberOfPaths > 2 && !destination.isDirectory()) return "Destination Path doesn't exist";

        for(int i = 0; i< numberOfPaths-1; i++){

            try {
                //Create File Objects
                String src = multiplePaths[i];
                Path sourcePath = Paths.get(src);

                //Check if file exists
                if(numberOfPaths == 2 && src == dest){
                    return "File Already Exists";
                }

                //Rename or Move File
                Files.move(sourcePath, destinPath.resolve(sourcePath.getFileName()));

                //Update file's Modification Date
                updateLastModified(destination);
                returnValue = "File Moved Successfully";


            } catch (Exception e) {
                returnValue = "move failed";
                break;
            }
        }

        return returnValue;



    }
}

