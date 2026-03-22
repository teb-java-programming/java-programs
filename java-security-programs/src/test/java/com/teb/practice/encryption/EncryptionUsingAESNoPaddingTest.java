package com.teb.practice.encryption;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.mockStatic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.mockito.MockedStatic;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

class EncryptionUsingAESNoPaddingTest {

    private static final String ENCRYPT_WORD = "wordToEncrypt";

    private EncryptionUsingAESNoPadding encryptionUsingAESNoPadding;

    @BeforeEach
    void setUp() {

        encryptionUsingAESNoPadding = new EncryptionUsingAESNoPadding();
    }

    @Test
    void testEncryptAndDecrypt() {

        byte[] encrypted = encryptionUsingAESNoPadding.encrypt(ENCRYPT_WORD);
        String decrypted = encryptionUsingAESNoPadding.decrypt(encrypted);

        assertNotNull(encrypted);
        assertTrue(encrypted.length > 0);
        assertEquals(ENCRYPT_WORD, decrypted);
    }

    @Test
    void testEncryptAndDecryptWithEmptyString() {

        byte[] encrypted = encryptionUsingAESNoPadding.encrypt("");
        String decrypted = encryptionUsingAESNoPadding.decrypt(encrypted);

        assertEquals("", decrypted);
    }

    @Test
    void testEncryptWithSameInputGeneratesDifferentCiphertext() {

        byte[] encryptedOne = encryptionUsingAESNoPadding.encrypt(ENCRYPT_WORD);
        byte[] encryptedTwo = encryptionUsingAESNoPadding.encrypt(ENCRYPT_WORD);

        assertNotEquals(new String(encryptedOne), new String(encryptedTwo));
    }

    @Test
    void testDecryptWithTamperedEncryptedDataThrowsException() {

        byte[] encrypted = encryptionUsingAESNoPadding.encrypt(ENCRYPT_WORD);

        // Manipulate encrypted data
        encrypted[encrypted.length - 1] ^= 1;

        assertThrows(RuntimeException.class, () -> encryptionUsingAESNoPadding.decrypt(encrypted));
    }

    @Test
    void testDecryptWithInvalidDataThrowsException() {

        byte[] invalid = new byte[] {1, 2, 3};

        assertThrows(RuntimeException.class, () -> encryptionUsingAESNoPadding.decrypt(invalid));
    }

    @Test
    void testConstructorWhenKeyGeneratorFailsThrowsException() {

        try (MockedConstruction<KeyGenerator> _ =
                mockConstruction(
                        KeyGenerator.class,
                        (mock, _) -> doThrow(new RuntimeException()).when(mock).init(anyInt()))) {
            RuntimeException e =
                    assertThrows(RuntimeException.class, EncryptionUsingAESNoPadding::new);
            assertTrue(e.getMessage().contains("Failed to initialize AES key"));
        }
    }

    @Test
    void testEncryptWhenCipherFailsThrowsException() {

        try (MockedStatic<Cipher> mockedCipher = mockStatic(Cipher.class)) {
            mockedCipher
                    .when(() -> Cipher.getInstance("AES/GCM/NoPadding"))
                    .thenThrow(new RuntimeException("Cipher failure"));

            RuntimeException e =
                    assertThrows(
                            RuntimeException.class,
                            () -> encryptionUsingAESNoPadding.encrypt(ENCRYPT_WORD));
            assertTrue(e.getMessage().contains("Encryption failed"));
        }
    }
}
