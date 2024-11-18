package org.example;

public class ExitCommand implements CommandInterface{
    @Override 
    public String execute(String... args) 
    {
        System.out.println("Exiting...");
        System.exit(0);
        return null;
    }
}