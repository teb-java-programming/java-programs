package com.teb.practice.encryption;

import static javax.crypto.Cipher.DECRYPT_MODE;
import static javax.crypto.Cipher.ENCRYPT_MODE;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionUsingAESPadding {

    private static final String ENCRYPT_ALGORITHM = "PBKDF2WithHmacSHA512";
    private static final String AES_CBC_PADDING = "AES/CBC/PKCS5Padding";
    protected SecretKey secretKey;

    protected byte[] encryptData(
            String textToEncrypt, SecretKey secretKey, IvParameterSpec ivSpec) {

        try {
            Cipher cipher = getCipherInstance();
            cipher.init(ENCRYPT_MODE, secretKey, ivSpec);

            return cipher.doFinal(textToEncrypt.getBytes());
        } catch (Exception e) {
            return e.getMessage().getBytes();
        }
    }

    protected String decryptData(
            byte[] bytesToDecrypt, SecretKey secretKey, IvParameterSpec ivSpec) {

        try {
            Cipher cipher = getCipherInstance();
            cipher.init(DECRYPT_MODE, secretKey, ivSpec);
            byte[] decryptedBytes = cipher.doFinal(bytesToDecrypt);

            return new String(decryptedBytes);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    protected SecretKey generateKeyFromPassword(String keyPassword, String keySalt) {

        try {
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ENCRYPT_ALGORITHM);
            KeySpec keySpec =
                    new PBEKeySpec(keyPassword.toCharArray(), keySalt.getBytes(), 65536, 256);
            secretKey = new SecretKeySpec(keyFactory.generateSecret(keySpec).getEncoded(), "AES");

            return secretKey;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected IvParameterSpec generateIv() {

        byte[] ivSpec = new byte[16];
        new SecureRandom().nextBytes(ivSpec);

        return new IvParameterSpec(ivSpec);
    }

    protected Cipher getCipherInstance() throws NoSuchAlgorithmException, NoSuchPaddingException {

        return Cipher.getInstance(AES_CBC_PADDING);
    }
}
