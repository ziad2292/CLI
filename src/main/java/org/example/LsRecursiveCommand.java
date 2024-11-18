package org.example;


import java.io.File;
import java.util.Arrays;
import java.util.Comparator;


public class LsRecursiveCommand implements LsInterface {
    @Override
    public String execute() {
        File cur = new File(System.getProperty("user.dir"));
        return lsRecursive(cur, "");
    }
    private String lsRecursive(File dir, String indent) {
        StringBuilder result = new StringBuilder();
        File[] files = dir.listFiles();
        if (files == null) {
            return "Error";
        }

        Arrays.sort(files, Comparator.comparing(File::getName));
        for (File file : files) {
            if (!file.isHidden()) {
                result.append(indent).append(file.getName()).append(file.isDirectory()? "/" :"").append("\n");
                if (file.isDirectory()) {
                    result.append(lsRecursive(file, indent + "  "));
                }
            }
        }
        return result.toString();
    }

}