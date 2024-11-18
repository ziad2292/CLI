package org.example;

import java.io.File;

public class TouchCommand extends CommandAbstract {

    @Override
    public String execute(String... args) {
        getMultiplePaths(args[0]);

        for (int i = 0; i < multiplePaths.length; i++) {
            try {
                // Create File Object
                File newFile = new File(multiplePaths[i]);

                if (!newFile.createNewFile()) {
                    // If the file exists, update its modification timestamp
                    updateLastModified(newFile);
                    returnValue = "File Timestamp Updated";
                } else {
                    returnValue = "File Created Successfully";
                }
            } catch (Exception e) {
                return e.toString();
            }
        }
        return returnValue;
    }
}
