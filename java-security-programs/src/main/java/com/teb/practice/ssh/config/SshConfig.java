package com.teb.practice.ssh.config;

public record SshConfig(
        String user,
        String host,
        int port,
        String password,
        String privateKeyPath,
        String passphrase,
        int maxAttempts,
        int connectTimeoutMillis) {}
