package com.company;

import java.math.BigInteger;
import java.security.SecureRandom;

import static com.company.KeyActions.saveKey;

public class KeyGenerator {

    public static void generateKeys(String filename) {
        SecureRandom rand = new SecureRandom();
        int bitLength = 4096;

        BigInteger p = new BigInteger(bitLength / 2, 100, rand);
        BigInteger q = new BigInteger(bitLength / 2, 100, rand);
        BigInteger n = p.multiply(q);
        BigInteger phiN = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        BigInteger e = new BigInteger("3");
        while (phiN.gcd(e).intValue() > 1) {
            e = e.add(new BigInteger("2"));
        }

        BigInteger d = e.modInverse(phiN);
        KeyPair publicKey = new KeyPair(e, n);
        KeyPair privateKey = new KeyPair(d, n);
        saveKey(filename + "_pub.key", publicKey);
        saveKey(filename + "_priv.key", privateKey);
    }

}
