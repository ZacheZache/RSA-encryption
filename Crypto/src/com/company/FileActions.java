package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileActions {

    public static String readFile(String path) {
        Scanner sc = null;
        try {
            sc = new Scanner(new File(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert sc != null;
        String text = sc.useDelimiter("\\A").next();
        sc.close();
        return text;
    }

    public static void writeToFile(String fileName, String encryptedMessage) {
        try {
            FileWriter myWriter = new FileWriter(fileName);
            myWriter.write(encryptedMessage);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
