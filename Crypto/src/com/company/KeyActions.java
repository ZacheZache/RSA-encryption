package com.company;

import java.io.*;

public class KeyActions {

    public static void saveKey(String fileName, KeyPair key) {
        try {
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(key);
            out.close();
            System.out.println("Saved key as " + fileName);

        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static KeyPair readKey(String fileName) {
        KeyPair key = null;
        try {
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            key = (KeyPair) in.readObject();
            in.close();
            System.out.println("Key: " + fileName + " is being used.");

        } catch (IOException | ClassNotFoundException i) {
            System.out.println("File not found, please try again");
        }
        return key;
    }

}
