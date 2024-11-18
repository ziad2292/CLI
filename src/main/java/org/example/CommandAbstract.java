package org.example;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public abstract class CommandAbstract implements CommandInterface{
    //Command Abstract Function
    public abstract String execute(String... args);
    protected String returnValue = "";

    //Set Path
    protected String path;
    public void setPath(String pathName){
        path = pathName;
    }

    public static void updateLastModified(File file){

        //Create Date Object & updaate last modified date
        Date currentDate = new Date();
        file.setLastModified(currentDate.getTime());
    }


    //Set Multiple Paths
    public String[] multiplePaths;

    public void getMultiplePaths(String longPath){
        multiplePaths = longPath.split(" ");
    }
}

