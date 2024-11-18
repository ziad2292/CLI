package org.example;

import java.io.File;

public class RmCommand extends CommandAbstract {

    @Override
    public String execute(String... args) {
        getMultiplePaths(args[0]);
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < multiplePaths.length; i++) {
            File file = new File(multiplePaths[i]);
            try {
                if (!file.delete()) {
                    result.append("Failed To Delete File: ").append(multiplePaths[i]).append("\n");
                } else {
                    result.append("File Deleted: ").append(multiplePaths[i]).append("\n");
                }
            } catch (Exception e) {
                result.append("Exception for File ").append(multiplePaths[i]).append(": ").append(e.toString()).append("\n");
            }
        }

        return result.toString().trim();
    }
}
