package com.teb.practice.security;

import static com.teb.practice.constants.Constants.SCAN;
import static java.lang.System.out;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class EncryptionUsingAESNoPadding {

    private static final int KEY_SIZE = 128;
    private static final int DATA_LENGTH = 128;
    private static SecretKey secretKey;
    private static Cipher encryptedCipher;

    private static void init() {

        KeyGenerator keyGenerator;
        try {
            keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(KEY_SIZE);
            secretKey = keyGenerator.generateKey();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private static byte[] encryptData(String textToEncrypt) {

        byte[] dataInBytes = textToEncrypt.getBytes();
        try {
            encryptedCipher = Cipher.getInstance("AES/GCM/NoPadding");
            encryptedCipher.init(Cipher.ENCRYPT_MODE, secretKey);

            return encryptedCipher.doFinal(dataInBytes);
        } catch (NoSuchAlgorithmException
                | NoSuchPaddingException
                | InvalidKeyException
                | IllegalBlockSizeException
                | BadPaddingException e) {
            return e.getMessage().getBytes();
        }
    }

    private static String decryptData(byte[] bytesToDecrypt) {

        try {
            Cipher decryptedCipher = Cipher.getInstance("AES/GCM/NoPadding");
            GCMParameterSpec gcmParamSpec =
                    new GCMParameterSpec(DATA_LENGTH, encryptedCipher.getIV());
            decryptedCipher.init(Cipher.DECRYPT_MODE, secretKey, gcmParamSpec);

            byte[] decryptedBytes = decryptedCipher.doFinal(bytesToDecrypt);
            return new String(decryptedBytes);
        } catch (NoSuchAlgorithmException
                | NoSuchPaddingException
                | InvalidKeyException
                | IllegalBlockSizeException
                | BadPaddingException
                | InvalidAlgorithmParameterException e) {
            return e.getMessage();
        }
    }

    public static void main(String[] args) {

        out.print("Enter string to encrypt: ");
        String stringToEncrypt = SCAN.nextLine();

        init();
        byte[] encryptedBytes = encryptData(stringToEncrypt);
        String decryptedString = decryptData(encryptedBytes);

        out.println("Encrypted string: " + new String(encryptedBytes, StandardCharsets.US_ASCII));
        out.println("Decrypted string: " + decryptedString);
    }
}
