package com.teb.practice.ssh.config;

import static java.lang.Integer.parseInt;
import static java.lang.Thread.currentThread;

import com.teb.practice.ssh.exception.SshException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SshConfigLoader {

    private String require(Properties properties, String key) {

        String value = properties.getProperty(key);

        if (value == null) {
            throw new SshException(key + " is required");
        }

        return value;
    }

    private int parseNumber(Properties properties, String key) {

        String value = require(properties, key);

        try {
            return parseInt(value);
        } catch (NumberFormatException e) {
            throw new SshException("Invalid number for " + key + ": " + value, e);
        }
    }

    protected SshConfig load(String resourcePath) {

        Properties properties = new Properties();
        InputStream input =
                currentThread().getContextClassLoader().getResourceAsStream(resourcePath);

        if (input == null) {
            throw new SshException("Config not found: " + resourcePath);
        }

        loadProperties(input, properties, resourcePath);

        return new SshConfig(
                require(properties, "ssh.user"),
                require(properties, "ssh.host"),
                parseNumber(properties, "ssh.port"),
                properties.getProperty("ssh.password"),
                properties.getProperty("ssh.privateKeyPath"),
                properties.getProperty("ssh.passphrase"),
                parseNumber(properties, "ssh.maxRetries"),
                parseNumber(properties, "ssh.connectTimeoutMillis"));
    }

    protected void loadProperties(InputStream input, Properties properties, String resourcePath) {

        try (input) {
            properties.load(input);
        } catch (IOException e) {
            throw new SshException("Failed to load config: " + resourcePath, e);
        }
    }
}
