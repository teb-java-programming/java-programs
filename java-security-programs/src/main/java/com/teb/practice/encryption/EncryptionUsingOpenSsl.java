package com.teb.practice.encryption;

import static java.nio.charset.StandardCharsets.US_ASCII;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Arrays.copyOfRange;
import static java.util.Base64.getDecoder;

import static javax.crypto.Cipher.DECRYPT_MODE;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionUsingOpenSsl {

    private static final String AES_CBC_PADDING = "AES/CBC/PKCS5Padding";
    private static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA256";
    private static final String AES_STANDARD = "AES";
    private static final int ITERATIONS = 200000;

    private final char[] password;

    public EncryptionUsingOpenSsl(char[] password) {
        this.password = password;
    }

    // OpenSSL command used to encrypt
    // openssl enc -aes-256-cbc -salt -pbkdf2 -iter 200000 -md sha256 -in <input-file> -out
    // <encrypted-file> -base64 -pass pass:<password>
    public String decryptData(String base64CipherText) {

        try {
            byte[] cipherBytes = getDecoder().decode(base64CipherText);
            byte[] header = copyOfRange(cipherBytes, 0, 8);
            String headerStr = new String(header, US_ASCII);

            if (!"Salted__".equals(headerStr)) {
                throw new IllegalArgumentException("Invalid OpenSSL encrypted data");
            }

            byte[] salt = copyOfRange(cipherBytes, 8, 16);
            byte[] cipherText = copyOfRange(cipherBytes, 16, cipherBytes.length);
            byte[] keyIv = deriveKeyAndIv(salt);
            byte[] keyBytes = copyOfRange(keyIv, 0, 32);
            byte[] iv = copyOfRange(keyIv, 32, 48);

            SecretKey finalKey = new SecretKeySpec(keyBytes, AES_STANDARD);
            Cipher cipher = Cipher.getInstance(AES_CBC_PADDING);
            cipher.init(DECRYPT_MODE, finalKey, new IvParameterSpec(iv));

            byte[] decrypted = cipher.doFinal(cipherText);

            return new String(decrypted, UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("Decryption failed", e);
        }
    }

    public String decryptFromResource(String resourcePath) throws IOException {

        try (InputStream inputStream =
                getClass().getClassLoader().getResourceAsStream(resourcePath)) {
            if (inputStream == null) {
                throw new IOException("Resource not found: " + resourcePath);
            }

            String content = new String(inputStream.readAllBytes(), UTF_8);

            return decryptData(content.trim());
        }
    }

    // OpenSSL compatible key and IV derivation using PBKDF2
    // 32 bytes key + 16 bytes IV = 48 bytes = 384 bits
    private byte[] deriveKeyAndIv(byte[] salt) throws GeneralSecurityException {

        PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, 384);
        SecretKeyFactory factory = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM);

        return factory.generateSecret(spec).getEncoded();
    }
}
