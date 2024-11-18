package org.example;


import java.io.File;

public class RmRecursive extends CommandAbstract{

    @Override
    public String execute(String... args){
        getMultiplePaths(args[0]);
        boolean flag = false;
        for(int i = 0; i < multiplePaths.length; i++){
            //Create File Object containing directory to be deleted
            File directory = new File(multiplePaths[i]);

            //Flag for deletion
            flag = deleteDirectory(directory);
        }

        if (flag) return "Directory Deleted Successfully";
        else return "Error Occured while deletion";
    }

    boolean deleteDirectory(File directoryToBeDeleted) {
        //Recursively delete all content in the directory
        try {
            File[] allContents = directoryToBeDeleted.listFiles();
            if (allContents != null) {
                for (File file : allContents) {
                    deleteDirectory(file);
                }
            }
            return directoryToBeDeleted.delete();


        } catch (Exception e) {
            return false;
        }
    }

}


