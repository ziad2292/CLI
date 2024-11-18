package org.example;


import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
public class MkdirCommand implements CommandInterface{




    @Override
    public String execute(String[] args) {
        String[] pr = args[0].split(" ");
        String currDir = System.getProperty("user.dir");
        if(currDir==null){
            currDir = Paths.get("").toAbsolutePath().toString();
        }

        for(String path: pr){
            File directory = new File(path);
            if(!directory.isAbsolute()){
                directory = new File(currDir,path);
            }
            if(!directory.exists()){
                boolean isCreated = directory.mkdirs();
                if(isCreated)continue;
                else{
                    return "Error while creating the "+directory ;
                }
            }
            else{
               return "Directory already exists";
            }
        }
        return "Directory created!";

    }

}



