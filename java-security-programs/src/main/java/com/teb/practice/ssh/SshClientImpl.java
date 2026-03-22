package com.teb.practice.ssh;

import static java.lang.System.currentTimeMillis;
import static java.lang.Thread.currentThread;
import static java.nio.charset.StandardCharsets.UTF_8;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.teb.practice.ssh.config.SshConfig;
import com.teb.practice.ssh.exception.SshException;
import com.teb.practice.ssh.util.SshUtil;

import java.io.InputStream;
import java.util.Properties;

public class SshClientImpl implements SshClient {

    private static final SshUtil sshUtil = new SshUtil();

    private final JSch jsch;
    private final Properties sshProperties;
    private final SshConfig config;

    public SshClientImpl(JSch jsch, Properties sshProperties, SshConfig config) {
        this.jsch = jsch;
        this.sshProperties = sshProperties;
        this.config = config;
    }

    @Override
    public String executeCommand(String command) {

        int attempt = 0;

        while (attempt < config.maxAttempts()) {
            try {
                return executeOnce(command);
            } catch (InterruptedException e) {
                currentThread().interrupt();

                throw new SshException("Interrupted while executing SSH command", e);
            } catch (Exception e) {
                attempt++;
                if (attempt >= config.maxAttempts()) {
                    throw new SshException("SSH command failed after " + attempt + " attempts", e);
                }
            }
        }

        throw new IllegalStateException("Unexpected retry loop exit");
    }

    @SuppressWarnings("BusyWait")
    private String executeOnce(String command) throws Exception {

        sshUtil.addIdentity(jsch, config);

        Session session = null;
        ChannelExec channel = null;

        try {
            session = jsch.getSession(config.user(), config.host(), config.port());

            if (config.password() != null) {
                session.setPassword(config.password());
            }

            session.setConfig(sshProperties);
            session.connect(config.connectTimeoutMillis());

            channel = (ChannelExec) session.openChannel("exec");
            channel.setCommand(command);

            InputStream input = channel.getInputStream();
            channel.connect();

            StringBuilder output = new StringBuilder();
            byte[] buffer = new byte[1024];

            long start = currentTimeMillis();

            while (!channel.isClosed()) {
                if (currentTimeMillis() - start > config.connectTimeoutMillis()) {
                    throw new SshException("SSH command timed out");
                }

                int read = input.read(buffer);
                if (read > 0) {
                    output.append(new String(buffer, 0, read, UTF_8));
                }

                Thread.sleep(50);
            }

            int read;
            while ((read = input.read(buffer)) != -1) {
                output.append(new String(buffer, 0, read, UTF_8));
            }

            return output.toString();
        } finally {
            if (channel != null) {
                channel.disconnect();
            }
            if (session != null) {
                session.disconnect();
            }
        }
    }
}
