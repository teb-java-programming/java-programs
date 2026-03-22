package com.teb.practice.ssh.exception;

public class SshException extends RuntimeException {

    public SshException(String message, Throwable cause) {
        super(message, cause);
    }

    public SshException(String message) {
        super(message);
    }
}
