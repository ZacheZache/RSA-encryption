package com.company;

import java.util.Scanner;

import static com.company.Encryption.decrypt;
import static com.company.Encryption.encrypt;
import static com.company.FileActions.readFile;
import static com.company.FileActions.writeToFile;
import static com.company.KeyActions.readKey;
import static com.company.KeyGenerator.generateKeys;

public class Menu {

    public static void runMenu() {
        String encrypted = "";
        int menuControl = 0;
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to this free encryption sevice!");

        while (menuControl != 9) {
            System.out.println("1. Generate key");
            System.out.println("2. Load keys and start encryption/decryption");
            System.out.println("9. Exit");

            int menuControl2 = 0;
            menuControl = sc.nextInt();
            sc.nextLine();
            switch (menuControl) {
                case 1:
                    System.out.println("Enter name of user: ");
                    String fileName = sc.nextLine();
                    System.out.println("Loading...");
                    generateKeys(fileName);
                    break;

                case 2:
                    while (menuControl2 != 9) {
                        System.out.println("Enter name of user to continue: ");
                        String loadFileName = sc.nextLine();

                        KeyPair publicKey = readKey(loadFileName + "_pub.key");
                        KeyPair privateKey = readKey(loadFileName + "_priv.key");

                        System.out.println("1. Encrypt");
                        System.out.println("2. Decrypt");
                        System.out.println("9. Back to main menu");

                        menuControl2 = sc.nextInt();
                        sc.nextLine();
                        switch (menuControl2) {
                            case 1:
                                System.out.println("Encrypt message from: ");
                                System.out.println("1. String");
                                System.out.println("2. File");
                                System.out.println("9. Back");

                                int controlE = sc.nextInt();
                                sc.nextLine();
                                if (controlE == 1) {
                                    System.out.println("Enter a message to encrypt");
                                    String messageString = sc.nextLine();
                                    encrypted = encrypt(messageString, publicKey);
                                    System.out.println("Your message is now encrypted, you can now decrypt it");
                                } else if (controlE == 2) {
                                    System.out.println("Enter the name of the file: ");
                                    String temp = sc.nextLine();
                                    String messageFileName = (temp + ".txt");
                                    String encryptedFileName = (temp + ".enc");
                                    String messageFromFile = readFile(messageFileName);
                                    String encryptedFileMessage = encrypt(messageFromFile, publicKey);
                                    writeToFile(encryptedFileName, encryptedFileMessage);
                                }

                            case 2:
                                System.out.println("Decrypt message from: ");
                                System.out.println("1. String");
                                System.out.println("2. File");
                                System.out.println("9. Back");

                                int controlD = sc.nextInt();
                                sc.nextLine();

                                if (controlD == 1) {
                                    String decrypted = decrypt(encrypted, privateKey);
                                    System.out.println("The decrypted message is:\n----------------\n" + decrypted + "\n----------------");

                                } else if (controlD == 2) {
                                    System.out.println("Enter the name of the file: ");
                                    String messageFileName = (sc.nextLine() + ".enc");
                                    String messageFromFile = readFile(messageFileName);
                                    String decrypted = decrypt(messageFromFile, privateKey);
                                    System.out.println("The decrypted message from " + messageFileName + " is:\n----------------\n" + decrypted + "\n----------------");

                                } else if (controlD == 9) {
                                    break;
                                }

                            case 9:
                                break;
                        }
                    }

                case 9:
                    break;
            }
        }
    }

}
