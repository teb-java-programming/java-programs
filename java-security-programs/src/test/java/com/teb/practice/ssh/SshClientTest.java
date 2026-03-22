package com.teb.practice.ssh;

import static org.apache.commons.lang3.ArrayUtils.arraycopy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static java.lang.Thread.currentThread;
import static java.nio.charset.StandardCharsets.UTF_8;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.teb.practice.ssh.config.SshConfig;
import com.teb.practice.ssh.exception.SshException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Properties;

@ExtendWith(MockitoExtension.class)
class SshClientTest {

    private static final String INPUT = "test-input";
    private static final String FAIL = "test-fail";
    private static final String EXEC = "exec";
    private static final String USER = "test-user";
    private static final String HOST = "test-host";
    private static final int PORT = 22;
    private static final int TIMEOUT = 1000;
    private static final String LIST_COMMAND = "ls";

    private JSch jSch;
    private Session session;
    private ChannelExec channel;
    private InputStream input;

    @Mock private SshConfig config;

    @BeforeEach
    void setUp() {

        jSch = mock(JSch.class);
        session = mock(Session.class);
        channel = mock(ChannelExec.class);
        input = new ByteArrayInputStream(INPUT.getBytes());
    }

    @Test
    void testExecutesCommandSuccessfully() throws Exception {

        when(jSch.getSession(any(), any(), anyInt())).thenReturn(session);
        when(session.openChannel(EXEC)).thenReturn(channel);
        when(channel.getInputStream()).thenReturn(input);
        when(channel.isClosed()).thenReturn(false, true);

        SshConfig config = new SshConfig(USER, HOST, PORT, null, null, null, 1, TIMEOUT);
        SshClientImpl client = new SshClientImpl(jSch, new Properties(), config);
        String result = client.executeCommand(LIST_COMMAND);

        assertEquals(INPUT, result);
    }

    @Test
    void testRetriesAndEventuallySucceeds() throws Exception {

        when(jSch.getSession(any(), any(), anyInt()))
                .thenThrow(new RuntimeException(FAIL))
                .thenReturn(session);
        when(session.openChannel(EXEC)).thenReturn(channel);
        when(channel.getInputStream()).thenReturn(input);
        when(channel.isClosed()).thenReturn(false, true);

        SshConfig config = new SshConfig(USER, HOST, PORT, null, null, null, 2, TIMEOUT);
        SshClientImpl client = new SshClientImpl(jSch, new Properties(), config);
        String result = client.executeCommand(LIST_COMMAND);

        assertEquals(INPUT, result);
    }

    @Test
    void testFailsAfterMaxRetries() throws JSchException {

        when(jSch.getSession(any(), any(), anyInt())).thenThrow(new RuntimeException(FAIL));

        SshConfig config = new SshConfig(USER, HOST, PORT, null, null, null, 2, TIMEOUT);
        SshClientImpl client = new SshClientImpl(jSch, new Properties(), config);

        assertThrows(SshException.class, () -> client.executeCommand(LIST_COMMAND));
    }

    @Test
    void testSetsPasswordWhenProvided() throws Exception {

        when(jSch.getSession(any(), any(), anyInt())).thenReturn(session);
        when(session.openChannel(EXEC)).thenReturn(channel);
        when(channel.getInputStream()).thenReturn(input);
        when(channel.isClosed()).thenReturn(false, true);

        SshConfig config = new SshConfig(USER, HOST, PORT, "password123", null, null, 1, TIMEOUT);
        SshClientImpl client = new SshClientImpl(jSch, new Properties(), config);
        client.executeCommand(LIST_COMMAND);

        verify(session).setPassword("password123");
    }

    @Test
    void testReturnsEmptyStringWhenNoOutput() throws Exception {

        input = new ByteArrayInputStream(new byte[0]);

        when(jSch.getSession(any(), any(), anyInt())).thenReturn(session);
        when(session.openChannel(EXEC)).thenReturn(channel);
        when(channel.getInputStream()).thenReturn(input);
        when(channel.isClosed()).thenReturn(false, true);

        SshConfig config = new SshConfig(USER, HOST, PORT, null, null, null, 1, TIMEOUT);
        SshClientImpl client = new SshClientImpl(jSch, new Properties(), config);
        String result = client.executeCommand(LIST_COMMAND);

        assertEquals("", result);
    }

    @Test
    void testThrowsExceptionWhenRetriesAreZero() {

        SshConfig config = new SshConfig(USER, HOST, PORT, null, null, null, 0, TIMEOUT);
        SshClientImpl client = new SshClientImpl(jSch, new Properties(), config);

        assertThrows(IllegalStateException.class, () -> client.executeCommand(LIST_COMMAND));
    }

    @Test
    void testHandlesInterruptedException() throws Exception {

        when(jSch.getSession(any(), any(), anyInt())).thenReturn(session);
        when(session.openChannel(EXEC)).thenReturn(channel);
        when(channel.getInputStream()).thenReturn(input);
        when(channel.isClosed()).thenReturn(false, true);

        SshConfig config = new SshConfig(USER, HOST, PORT, null, null, null, 1, TIMEOUT);
        SshClientImpl client = new SshClientImpl(jSch, new Properties(), config);

        currentThread().interrupt();

        assertThrows(SshException.class, () -> client.executeCommand(LIST_COMMAND));
    }

    @Test
    void testThrowsExceptionWhenTimeoutExceeded() throws Exception {

        input = new ByteArrayInputStream(new byte[0]);

        when(jSch.getSession(any(), any(), anyInt())).thenReturn(session);
        when(session.openChannel(EXEC)).thenReturn(channel);
        when(channel.getInputStream()).thenReturn(input);
        // Never closes to force timeout
        when(channel.isClosed()).thenReturn(false);

        SshConfig config = new SshConfig(USER, HOST, PORT, null, null, null, 1, 10);
        SshClientImpl client = new SshClientImpl(jSch, new Properties(), config);

        assertThrows(SshException.class, () -> client.executeCommand(LIST_COMMAND));
    }

    @Test
    void testHandlesStreamEnd() throws Exception {

        when(jSch.getSession(any(), any(), anyInt())).thenReturn(session);
        when(session.openChannel(EXEC)).thenReturn(channel);
        when(channel.getInputStream()).thenReturn(input);
        when(channel.isClosed()).thenReturn(false, true);

        SshConfig config = new SshConfig(USER, HOST, PORT, null, null, null, 1, TIMEOUT);
        SshClientImpl client = new SshClientImpl(jSch, new Properties(), config);
        String result = client.executeCommand(LIST_COMMAND);

        assertEquals(INPUT, result);
    }

    @Test
    void testStreamDrainsAfterFinalLoop() throws Exception {

        when(config.user()).thenReturn("user");
        when(config.host()).thenReturn("host");
        when(config.port()).thenReturn(22);
        when(config.password()).thenReturn(null);
        when(config.connectTimeoutMillis()).thenReturn(5000);
        when(config.maxAttempts()).thenReturn(1);

        when(jSch.getSession(any(), any(), anyInt())).thenReturn(session);
        when(session.openChannel(EXEC)).thenReturn(channel);

        doNothing().when(session).connect(anyInt());
        doNothing().when(channel).connect();

        InputStream input = mock(InputStream.class);
        byte[] first = "test-".getBytes(UTF_8);
        byte[] second = "input".getBytes(UTF_8);

        when(input.read(any(byte[].class)))
                .thenAnswer(
                        invocation -> {
                            byte[] buffer = invocation.getArgument(0);
                            arraycopy(first, 0, buffer, 0, first.length);
                            return first.length;
                        })
                .thenAnswer(
                        invocation -> {
                            byte[] buffer = invocation.getArgument(0);
                            arraycopy(second, 0, buffer, 0, second.length);
                            return second.length;
                        })
                .thenReturn(-1);

        when(channel.getInputStream()).thenReturn(input);
        // Exits to leave data unread
        when(channel.isClosed()).thenReturn(false).thenReturn(true);

        SshClientImpl client = new SshClientImpl(jSch, new Properties(), config);
        String result = client.executeCommand(LIST_COMMAND);

        assertEquals(INPUT, result);
    }
}
