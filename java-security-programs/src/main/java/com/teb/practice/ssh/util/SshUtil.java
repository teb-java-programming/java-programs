package com.teb.practice.ssh.util;

import static java.util.Objects.nonNull;

import com.jcraft.jsch.JSch;
import com.teb.practice.ssh.config.SshConfig;
import com.teb.practice.ssh.exception.SshException;

public class SshUtil {

    public void addIdentity(JSch jsch, SshConfig config) {

        try {
            String keyPath = config.privateKeyPath();

            if (keyPath == null || keyPath.isBlank()) {
                return;
            }

            String passphrase = config.passphrase();
            if (nonNull(passphrase) && !passphrase.isBlank()) {
                jsch.addIdentity(keyPath, passphrase);
            } else {
                jsch.addIdentity(keyPath);
            }
        } catch (Exception e) {
            throw new SshException("Failed to load SSH private key", e);
        }
    }
}
