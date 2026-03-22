package com.teb.practice.ssh.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.teb.practice.ssh.config.SshConfig;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SshUtilTest {

    private static final String USER = "test-user";
    private static final String HOST = "test-host";
    private static final int PORT = 22;
    private static final int TIMEOUT = 1000;
    private static final String KEY_PATH = "/path/key";
    private static final SshUtil sshUtil = new SshUtil();

    private JSch jSch;

    @BeforeEach
    void setUp() {

        jSch = mock(JSch.class);
    }

    @Test
    void testAddsIdentityWithoutPassphrase() throws Exception {

        SshConfig config = new SshConfig(USER, HOST, PORT, null, KEY_PATH, null, 1, TIMEOUT);
        sshUtil.addIdentity(jSch, config);

        verify(jSch).addIdentity(KEY_PATH);
    }

    @Test
    void testAddsIdentityWithPassphrase() throws Exception {

        SshConfig config = new SshConfig(USER, HOST, PORT, null, KEY_PATH, "secret", 1, TIMEOUT);
        sshUtil.addIdentity(jSch, config);

        verify(jSch).addIdentity(KEY_PATH, "secret");
    }

    @Test
    void testSkipsWhenPrivateKeyPathIsNull() {

        SshConfig config = new SshConfig(USER, HOST, PORT, null, null, null, 1, TIMEOUT);
        sshUtil.addIdentity(jSch, config);

        verifyNoInteractions(jSch);
    }

    @Test
    void testSkipsWhenPrivateKeyPathIsBlank() {

        SshConfig config = new SshConfig(USER, HOST, PORT, null, "", null, 1, TIMEOUT);
        sshUtil.addIdentity(jSch, config);

        verifyNoInteractions(jSch);
    }

    @Test
    void testSkipsWhenPassphraseIsBlank() throws Exception {

        SshConfig config = new SshConfig(USER, HOST, PORT, null, KEY_PATH, "", 1, TIMEOUT);
        sshUtil.addIdentity(jSch, config);

        verify(jSch).addIdentity(KEY_PATH);
    }

    @Test
    void testThrowsSshExceptionWhenAddIdentityFails() throws Exception {

        doThrow(new JSchException("failure")).when(jSch).addIdentity(anyString());

        SshConfig config = new SshConfig(USER, HOST, PORT, null, KEY_PATH, null, 1, TIMEOUT);

        RuntimeException e =
                assertThrows(RuntimeException.class, () -> sshUtil.addIdentity(jSch, config));
        assertEquals("Failed to load SSH private key", e.getMessage());
    }
}
