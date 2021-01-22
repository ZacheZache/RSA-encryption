package com.company;

import java.math.BigInteger;

public class Encryption {

    public static String encrypt(String message, KeyPair key) {
            return (new BigInteger(message.getBytes())).modPow(key.getKey(), key.getN()).toString();
    }

    public static String decrypt(String message, KeyPair key) {
        return new String((new BigInteger(message)).modPow(key.getKey(), key.getN()).toByteArray());
    }

    /*
    (Encrypt) m^e mod n = c
    String cipher = msg.modPow(e, n).toString();

    c^d mod d (Decrypt)
    String plain = new String((new BigInteger(cipher)).modPow(d, n).toByteArray());
    */

}
