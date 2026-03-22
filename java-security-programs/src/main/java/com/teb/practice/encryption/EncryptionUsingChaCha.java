package com.teb.practice.encryption;

import static org.apache.commons.lang3.ArrayUtils.arraycopy;

import static java.nio.charset.StandardCharsets.UTF_8;

import static javax.crypto.Cipher.DECRYPT_MODE;
import static javax.crypto.Cipher.ENCRYPT_MODE;
import static javax.crypto.Cipher.getInstance;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.ChaCha20ParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionUsingChaCha {

    private static final Logger LOGGER = Logger.getLogger(EncryptionUsingChaCha.class.getName());
    private static final int NONCE_SIZE = 12;
    private static final int KEY_SIZE = 32;
    private static final String CHA_CHA_ALGORITHM = "ChaCha20";

    protected void encryptData(
            String encryptionKey, InputStream inputStream, OutputStream outputStream) {

        dataEncryptDecrypt(encryptionKey, ENCRYPT_MODE, inputStream, outputStream);
    }

    protected void decryptData(
            String encryptionKey, InputStream inputStream, OutputStream outputStream) {

        dataEncryptDecrypt(encryptionKey, DECRYPT_MODE, inputStream, outputStream);
    }

    protected void dataEncryptDecrypt(
            String encryptionKey,
            int actionMode,
            InputStream inputStream,
            OutputStream outputStream) {

        try {
            byte[] keyBytes = normalizeKey(encryptionKey);
            Key key = new SecretKeySpec(keyBytes, CHA_CHA_ALGORITHM);
            Cipher cipher = getInstance(CHA_CHA_ALGORITHM);
            byte[] nonceBytes = new byte[NONCE_SIZE];
            ChaCha20ParameterSpec paramSpec = new ChaCha20ParameterSpec(nonceBytes, 1);

            if (actionMode == ENCRYPT_MODE) {
                new SecureRandom().nextBytes(nonceBytes);
                outputStream.write(nonceBytes);

                cipher.init(actionMode, key, paramSpec);

                CipherInputStream cipherStream = new CipherInputStream(inputStream, cipher);
                doCopy(cipherStream, outputStream);
            } else if (actionMode == DECRYPT_MODE) {
                readComplete(inputStream, nonceBytes);

                cipher.init(actionMode, key, paramSpec);

                CipherOutputStream cipherStream = new CipherOutputStream(outputStream, cipher);
                doCopy(inputStream, cipherStream);
            }

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Encryption/Decryption error", e);
        }
    }

    private byte[] normalizeKey(String key) {

        byte[] keyBytes = new byte[KEY_SIZE];
        byte[] inputBytes = key.getBytes(UTF_8);

        arraycopy(inputBytes, 0, keyBytes, 0, Math.min(inputBytes.length, KEY_SIZE));

        return keyBytes;
    }

    private void readComplete(InputStream inputStream, byte[] buffer) throws IOException {

        int offset = 0;
        int bytesRead;

        while (offset < buffer.length
                && (bytesRead = inputStream.read(buffer, offset, buffer.length - offset)) != -1) {
            offset += bytesRead;
        }

        if (offset < buffer.length) {
            throw new IOException("Unable to read required nonce bytes");
        }
    }

    private void doCopy(InputStream inputStream, OutputStream outputStream) throws IOException {

        byte[] dataInBytes = new byte[64];
        int numberBytes;

        while ((numberBytes = inputStream.read(dataInBytes)) != -1) {
            outputStream.write(dataInBytes, 0, numberBytes);
        }

        outputStream.flush();
        outputStream.close();
        inputStream.close();
    }
}
