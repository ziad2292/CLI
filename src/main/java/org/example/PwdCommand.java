package org.example;


public class PwdCommand implements CommandInterface{
    @Override 
    public String execute(String... args) {

        return  "Current Directory: " + System.getProperty("user.dir");

}
}




