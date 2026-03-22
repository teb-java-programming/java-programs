package com.teb.practice.encryption;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

import static javax.crypto.Cipher.DECRYPT_MODE;
import static javax.crypto.Cipher.ENCRYPT_MODE;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

class EncryptionUsingAESPaddingTest {

    private static final String PASSWORD = "password";
    private static final String SALT = "salt";
    private static final String ENCRYPT_WORD = "wordToEncrypt";
    private static final String FORCED_ERROR = "Throwing forced error";

    private EncryptionUsingAESPadding encryptionUsingAESPadding;

    @BeforeEach
    void setUp() {

        encryptionUsingAESPadding = new EncryptionUsingAESPadding();
    }

    @Test
    void testGenerateKeyFromPassword() {

        SecretKey key = encryptionUsingAESPadding.generateKeyFromPassword(PASSWORD, SALT);

        assertNotNull(key);
        assertNotNull(encryptionUsingAESPadding.secretKey);
    }

    @Test
    void testGenerateIv() {

        IvParameterSpec iv = encryptionUsingAESPadding.generateIv();

        assertNotNull(iv);
        assertEquals(16, iv.getIV().length);
    }

    @Test
    void testEncryptAndDecrypt() {

        SecretKey key = encryptionUsingAESPadding.generateKeyFromPassword(PASSWORD, SALT);
        IvParameterSpec iv = encryptionUsingAESPadding.generateIv();

        byte[] encrypted = encryptionUsingAESPadding.encryptData(ENCRYPT_WORD, key, iv);
        String decrypted = encryptionUsingAESPadding.decryptData(encrypted, key, iv);

        assertEquals(ENCRYPT_WORD, decrypted);
    }

    @Test
    void testEncryptException() throws Exception {

        EncryptionUsingAESPadding spy = spy(new EncryptionUsingAESPadding());
        Cipher mockCipher = mock(Cipher.class);

        doReturn(mockCipher).when(spy).getCipherInstance();
        doThrow(new RuntimeException(FORCED_ERROR))
                .when(mockCipher)
                .init(eq(ENCRYPT_MODE), any(), any(IvParameterSpec.class));

        byte[] result = spy.encryptData(ENCRYPT_WORD, mock(SecretKey.class), spy.generateIv());

        assertTrue(new String(result).contains(FORCED_ERROR));
    }

    @Test
    void testDecryptException() throws Exception {

        EncryptionUsingAESPadding spy = spy(new EncryptionUsingAESPadding());
        Cipher mockCipher = mock(Cipher.class);

        doReturn(mockCipher).when(spy).getCipherInstance();
        doThrow(new RuntimeException(FORCED_ERROR))
                .when(mockCipher)
                .init(eq(DECRYPT_MODE), any(), any(IvParameterSpec.class));

        String result =
                spy.decryptData(new byte[] {1, 2, 3}, mock(SecretKey.class), spy.generateIv());

        assertTrue(result.contains(FORCED_ERROR));
    }

    @Test
    void testDecryptBadPaddingPath() {

        SecretKey key = encryptionUsingAESPadding.generateKeyFromPassword(PASSWORD, SALT);
        IvParameterSpec iv = encryptionUsingAESPadding.generateIv();

        byte[] encrypted = encryptionUsingAESPadding.encryptData(ENCRYPT_WORD, key, iv);
        encrypted[0] = (byte) ~encrypted[0];

        String result = encryptionUsingAESPadding.decryptData(encrypted, key, iv);

        assertNotEquals(ENCRYPT_WORD, result);
    }

    @Test
    void testKeyGenerationException() {

        EncryptionUsingAESPadding spyPadding = spy(new EncryptionUsingAESPadding());

        assertThrows(RuntimeException.class, () -> spyPadding.generateKeyFromPassword(null, SALT));
    }
}
