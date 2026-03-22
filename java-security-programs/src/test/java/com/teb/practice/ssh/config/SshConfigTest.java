package com.teb.practice.ssh.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.teb.practice.ssh.exception.SshException;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

class SshConfigTest {

    private final SshConfigLoader sshConfigLoader = new SshConfigLoader();

    @Test
    void testLoadsConfigSuccessfully() {

        SshConfig config = sshConfigLoader.load("ssh.properties");

        assertEquals("user", config.user());
        assertEquals("host", config.host());
        assertEquals(22, config.port());
        assertEquals("password", config.password());
        assertEquals(2, config.maxAttempts());
        assertEquals(1000, config.connectTimeoutMillis());
    }

    @Test
    void testThrowsExceptionWhenConfigFileNotFound() {

        SshException e =
                assertThrows(SshException.class, () -> sshConfigLoader.load("missing.properties"));
        assertEquals("Config not found: missing.properties", e.getMessage());
    }

    @Test
    void testThrowsExceptionWhenRequiredPropertyMissing() {

        SshException e =
                assertThrows(
                        SshException.class, () -> sshConfigLoader.load("missing-port.properties"));
        assertTrue(e.getMessage().contains("ssh.port is required"));
    }

    @Test
    void testThrowsExceptionWhenPropertyIsNotValidType() {

        SshException e =
                assertThrows(
                        SshException.class, () -> sshConfigLoader.load("invalid-port.properties"));
        assertEquals("Invalid number for ssh.port: abc", e.getMessage());
    }

    @Test
    void testThrowsExceptionWhenUserMissing() {

        SshException e =
                assertThrows(
                        SshException.class, () -> sshConfigLoader.load("missing-user.properties"));
        assertTrue(e.getMessage().contains("ssh.user is required"));
    }

    @SuppressWarnings("resource")
    @Test
    void shouldThrowWhenInputStreamFailsDuringRead() {

        InputStream failingStream =
                new InputStream() {
                    @Override
                    public int read() throws IOException {
                        throw new IOException("Read failure");
                    }
                };

        SshException e =
                assertThrows(
                        SshException.class,
                        () ->
                                sshConfigLoader.loadProperties(
                                        failingStream, new Properties(), "test"));
        assertTrue(e.getMessage().contains("Failed to load config"));
        assertInstanceOf(IOException.class, e.getCause());
    }
}
