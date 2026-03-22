package com.teb.practice.encryption;

import static org.apache.commons.lang3.ArrayUtils.arraycopy;

import static java.util.Arrays.copyOfRange;

import static javax.crypto.Cipher.DECRYPT_MODE;
import static javax.crypto.Cipher.ENCRYPT_MODE;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;

public class EncryptionUsingAESNoPadding {

    private static final int KEY_SIZE = 128;
    private static final int GCM_TAG_LENGTH = 128;
    private static final int IV_LENGTH = 12;
    private static final String AES_GCM_PADDING = "AES/GCM/NoPadding";
    private final SecretKey secretKey;

    public EncryptionUsingAESNoPadding() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(KEY_SIZE);
            this.secretKey = keyGenerator.generateKey();
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize AES key", e);
        }
    }

    protected byte[] encrypt(String textToEncrypt) {

        try {
            byte[] iv = new byte[IV_LENGTH];
            SecureRandom random = new SecureRandom();
            random.nextBytes(iv);

            Cipher cipher = Cipher.getInstance(AES_GCM_PADDING);
            GCMParameterSpec spec = new GCMParameterSpec(GCM_TAG_LENGTH, iv);
            cipher.init(ENCRYPT_MODE, secretKey, spec);

            byte[] cipherText = cipher.doFinal(textToEncrypt.getBytes());

            // Prepend IV to ciphertext
            byte[] result = new byte[iv.length + cipherText.length];
            arraycopy(iv, 0, result, 0, iv.length);
            arraycopy(cipherText, 0, result, iv.length, cipherText.length);

            return result;
        } catch (Exception e) {
            throw new RuntimeException("Encryption failed", e);
        }
    }

    protected String decrypt(byte[] bytesToDecrypt) {

        try {
            byte[] iv = copyOfRange(bytesToDecrypt, 0, IV_LENGTH);
            byte[] cipherText = copyOfRange(bytesToDecrypt, IV_LENGTH, bytesToDecrypt.length);

            Cipher cipher = Cipher.getInstance(AES_GCM_PADDING);
            GCMParameterSpec spec = new GCMParameterSpec(GCM_TAG_LENGTH, iv);
            cipher.init(DECRYPT_MODE, secretKey, spec);

            byte[] decryptedBytes = cipher.doFinal(cipherText);

            return new String(decryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("Decryption failed", e);
        }
    }
}
