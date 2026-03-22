package com.teb.practice.encryption;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static javax.crypto.Cipher.DECRYPT_MODE;
import static javax.crypto.Cipher.ENCRYPT_MODE;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

class EncryptionUsingChaChaTest {

    private static final String KEY = "my-secret-key";
    private static final String ENCRYPT_WORD = "wordToEncrypt";

    private ByteArrayOutputStream encryptStream;
    private ByteArrayOutputStream decryptStream;
    private EncryptionUsingChaCha encryptionUsingChaCha;

    @BeforeEach
    void setup() {

        encryptionUsingChaCha = new EncryptionUsingChaCha();
        encryptStream = new ByteArrayOutputStream();
        decryptStream = new ByteArrayOutputStream();
    }

    @Test
    void testEncryptAndDecrypt() {

        encryptionUsingChaCha.encryptData(
                KEY, new ByteArrayInputStream(ENCRYPT_WORD.getBytes()), encryptStream);
        encryptionUsingChaCha.decryptData(
                KEY, new ByteArrayInputStream(encryptStream.toByteArray()), decryptStream);

        assertEquals(ENCRYPT_WORD, decryptStream.toString());
    }

    @Test
    void testEncryptAndDecryptWithEmptyInput() {

        encryptionUsingChaCha.encryptData(
                KEY, new ByteArrayInputStream(new byte[0]), encryptStream);
        encryptionUsingChaCha.decryptData(
                KEY, new ByteArrayInputStream(encryptStream.toByteArray()), decryptStream);

        assertEquals("", decryptStream.toString());
    }

    @Test
    void testEncryptAndDecryptWithLargeInput() {

        String original = ENCRYPT_WORD.repeat(2000);

        encryptionUsingChaCha.encryptData(
                KEY, new ByteArrayInputStream(original.getBytes()), encryptStream);
        encryptionUsingChaCha.decryptData(
                KEY, new ByteArrayInputStream(encryptStream.toByteArray()), decryptStream);

        assertEquals(original, decryptStream.toString());
    }

    @Test
    void testWritesNonceAtStart() {

        encryptionUsingChaCha.encryptData(
                KEY, new ByteArrayInputStream("data".getBytes()), encryptStream);

        byte[] result = encryptStream.toByteArray();

        assertTrue(result.length > 12);
        assertEquals(12, result.length - "data".getBytes().length);
    }

    @Test
    void testInvalidNonceValue() {

        assertDoesNotThrow(
                () ->
                        encryptionUsingChaCha.decryptData(
                                KEY, new ByteArrayInputStream(new byte[5]), decryptStream));
        assertEquals("", decryptStream.toString());
    }

    @Test
    void testDataEncryptDecrypt() {

        encryptionUsingChaCha.dataEncryptDecrypt(
                KEY,
                ENCRYPT_MODE,
                new ByteArrayInputStream(ENCRYPT_WORD.getBytes()),
                encryptStream);
        encryptionUsingChaCha.dataEncryptDecrypt(
                KEY,
                DECRYPT_MODE,
                new ByteArrayInputStream(encryptStream.toByteArray()),
                decryptStream);

        assertTrue(encryptStream.size() > 12);
        assertEquals(ENCRYPT_WORD, decryptStream.toString());
    }

    @Test
    void testUnsupportedActionMode() {

        assertDoesNotThrow(
                () ->
                        encryptionUsingChaCha.dataEncryptDecrypt(
                                KEY,
                                -1,
                                new ByteArrayInputStream(ENCRYPT_WORD.getBytes()),
                                encryptStream));
        assertEquals("", encryptStream.toString());
    }

    @Test
    void testSlowStream() {

        InputStream slowStream =
                new InputStream() {
                    private final byte[] data = ENCRYPT_WORD.getBytes();
                    private int index = 0;

                    @SuppressWarnings("NullableProblems")
                    @Override
                    public int read(byte[] b, int off, int len) {
                        if (index >= data.length) return -1;
                        b[off] = data[index++];
                        return 1;
                    }

                    @Override
                    public int read() {
                        return ((index < data.length) ? data[index++] : -1) & 0xFF;
                    }
                };

        encryptionUsingChaCha.encryptData(KEY, slowStream, encryptStream);
        encryptionUsingChaCha.decryptData(
                KEY, new ByteArrayInputStream(encryptStream.toByteArray()), decryptStream);

        assertEquals(ENCRYPT_WORD, decryptStream.toString());
    }
}
