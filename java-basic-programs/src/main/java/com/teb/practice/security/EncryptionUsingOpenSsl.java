package com.teb.practice.security;

import static java.lang.System.arraycopy;
import static java.lang.System.out;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Properties;

public class EncryptionUsingOpenSsl {

    static final String ENCRYPT_PADDING = "AES/CBC/PKCS5Padding";
    static final String ENCRYPTED_FILE_PATH = "src/main/resources/open-ssl-cipher-text.enc";
    // theencodedbong
    static byte[] keyPassword = System.getenv("keyPassword").getBytes();

    private static String decryptData(String textToDecrypt) {

        try {
            // Parse cipher text
            byte[] cipherBytes = Base64.getDecoder().decode(textToDecrypt);
            byte[] saltBytes = Arrays.copyOfRange(cipherBytes, 8, 16);
            cipherBytes = Arrays.copyOfRange(cipherBytes, 16, cipherBytes.length);

            // Derive secret key
            byte[] passSaltBytes = concat(keyPassword, saltBytes);
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-512/256");
            byte[] keyBytes = messageDigest.digest(passSaltBytes);
            SecretKey secretKey = new SecretKeySpec(keyBytes, "AES");

            // Derive IV
            messageDigest.reset();
            byte[] iv =
                    Arrays.copyOfRange(
                            messageDigest.digest(concat(keyBytes, passSaltBytes)), 0, 16);

            Cipher cipher = Cipher.getInstance(ENCRYPT_PADDING);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(iv));
            return new String(cipher.doFinal(cipherBytes)).trim();
        } catch (NoSuchAlgorithmException
                | NoSuchPaddingException
                | InvalidKeyException
                | InvalidAlgorithmParameterException
                | IllegalBlockSizeException
                | BadPaddingException e) {
            return e.getMessage();
        }
    }

    private static byte[] concat(byte[] key, byte[] salt) {

        byte[] hash = new byte[key.length + salt.length];
        arraycopy(key, 0, hash, 0, key.length);
        arraycopy(salt, 0, hash, key.length, salt.length);
        return hash;
    }

    public static void main(String[] args) {

        Properties properties = new Properties();
        try (InputStream inputStream = new FileInputStream(ENCRYPTED_FILE_PATH)) {
            properties.load(inputStream);

            String decryptedString =
                    decryptData(properties.entrySet().iterator().next().getKey().toString());

            out.println("Decrypted string: " + decryptedString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
