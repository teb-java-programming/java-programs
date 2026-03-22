package com.teb.practice.encryption;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Base64.getEncoder;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Properties;

public class EncryptionUsingOpenSslTest {

    private static final String ENCRYPTED_FILE_PATH = "open-ssl-cipher-text.enc";
    private static final String DECRYPTED_TEXT = "JavaProgrammingByTheEncodedBong";
    private static final String PASSWORD = "thisispassword";
    private static final String NON_BASE64 = "non-base64";

    private final EncryptionUsingOpenSsl encryptionUsingOpenSsl =
            new EncryptionUsingOpenSsl(PASSWORD.toCharArray());

    @Test
    void testDecryptsFromResourceFile() throws IOException {

        String result = encryptionUsingOpenSsl.decryptFromResource(ENCRYPTED_FILE_PATH);

        assertEquals(DECRYPTED_TEXT, result);
    }

    @Test
    void testDecryptsUsingProperties() {

        try (var inputStream =
                getClass().getClassLoader().getResourceAsStream(ENCRYPTED_FILE_PATH)) {

            if (inputStream == null) {
                fail("Resource not found: " + ENCRYPTED_FILE_PATH);
            }

            Properties properties = new Properties();
            properties.load(inputStream);

            String cipherText = properties.entrySet().iterator().next().getKey().toString();
            String result = encryptionUsingOpenSsl.decryptData(cipherText);

            assertEquals(DECRYPTED_TEXT, result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testFailsForInvalidBase64() {

        RuntimeException e =
                assertThrows(
                        RuntimeException.class,
                        () -> encryptionUsingOpenSsl.decryptData(NON_BASE64));

        assertTrue(e.getMessage().contains("Decryption failed"));
    }

    @Test
    void testFailsForInvalidHeader() {

        String invalidBase64 = getEncoder().encodeToString(NON_BASE64.getBytes(UTF_8));

        RuntimeException e =
                assertThrows(
                        RuntimeException.class,
                        () -> encryptionUsingOpenSsl.decryptData(invalidBase64));
        assertTrue(e.getMessage().contains("Decryption failed"));
    }

    @Test
    void testThrowsExceptionWhenResourceNotFound() {

        IOException e =
                assertThrows(
                        IOException.class,
                        () -> encryptionUsingOpenSsl.decryptFromResource("missing.enc"));
        assertTrue(e.getMessage().contains("Resource not found"));
    }
}
