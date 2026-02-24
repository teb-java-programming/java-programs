package com.teb.practice.security;

import static com.teb.practice.constants.Constants.SCAN;
import static java.lang.System.out;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

public class EncryptionUsingAESPadding {

    static final String ENCRYPT_ALGORITHM = "PBKDF2WithHmacSHA512";
    static final String ENCRYPT_PADDING = "AES/CBC/PKCS5Padding";
    // TheEncodedBongByKuntal
    static String keyPassword = System.getenv("keyPassword");
    static String keySalt = System.getenv("keySalt");
    private static SecretKey secretKey;

    private static void generateKeyFromPassword(String keyPassword, String keySalt) {

        try {
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ENCRYPT_ALGORITHM);
            KeySpec keySpec =
                    new PBEKeySpec(keyPassword.toCharArray(), keySalt.getBytes(), 65536, 256);
            secretKey = new SecretKeySpec(keyFactory.generateSecret(keySpec).getEncoded(), "AES");
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }

    private static IvParameterSpec generateIv() {

        byte[] ivSpec = new byte[16];
        // Generate random bytes to initialise the cipher
        new SecureRandom().nextBytes(ivSpec);
        return new IvParameterSpec(ivSpec);
    }

    private static byte[] encryptData(
            String textToEncrypt, SecretKey secretKey, IvParameterSpec ivSpec) {

        try {
            Cipher cipher = Cipher.getInstance(ENCRYPT_PADDING);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);
            return cipher.doFinal(textToEncrypt.getBytes());
        } catch (NoSuchAlgorithmException
                | NoSuchPaddingException
                | InvalidKeyException
                | InvalidAlgorithmParameterException
                | IllegalBlockSizeException
                | BadPaddingException e) {
            return e.getMessage().getBytes();
        }
    }

    private static String decryptData(
            byte[] bytesToDecrypt, SecretKey secretKey, IvParameterSpec ivSpec) {

        try {
            Cipher cipher = Cipher.getInstance(ENCRYPT_PADDING);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);
            byte[] decryptedBytes = cipher.doFinal(bytesToDecrypt);
            return new String(decryptedBytes);
        } catch (NoSuchAlgorithmException
                | NoSuchPaddingException
                | InvalidKeyException
                | InvalidAlgorithmParameterException
                | IllegalBlockSizeException
                | BadPaddingException e) {
            return e.getMessage();
        }
    }

    public static void main(String[] args) {

        out.print("Enter string to encrypt: ");
        String stringToEncrypt = SCAN.nextLine();

        generateKeyFromPassword(keyPassword, keySalt);
        IvParameterSpec ivSpec = generateIv();

        byte[] encryptedBytes = encryptData(stringToEncrypt, secretKey, ivSpec);
        String decryptedString = decryptData(encryptedBytes, secretKey, ivSpec);

        out.println(
                "Encrypted string: " + new String(encryptedBytes, StandardCharsets.US_ASCII));
        out.println("Decrypted string: " + decryptedString);
    }
}
