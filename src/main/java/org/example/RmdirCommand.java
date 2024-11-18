package org.example;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
public class RmdirCommand implements CommandInterface {





    @Override
    public String execute(String... args) {
        try {
            removeDir(args[0]);
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return "";
    }

    private void removeDir(String dir) throws  IOException{
        Path path = Paths.get(dir);

        if(!Files.exists(path)){
            System.out.println("Error directory does not exits");
            return;
        }

        if(!Files.isDirectory(path)){
            System.out.println("The specified path is not a directory");
            return;
        }

        if(Files.list(path).findAny().isPresent()){
            System.out.println("Directory is not empty");
            return;
        }

        Files.delete(path);
    }

}