package com.teb.practice.concurrency;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static java.lang.Thread.currentThread;

import lombok.SneakyThrows;

import org.junit.jupiter.api.Test;

class UsingAtLockedTest {

    private final UsingAtLocked usingAtLocked = new UsingAtLocked();

    private double amount = 100;

    @SneakyThrows
    @Test
    void testCounter() {

        Runnable runnable =
                () -> {
                    for (int i = 0; i < 5; i++) {
                        usingAtLocked.deposit(amount++);
                        usingAtLocked.printStatement();
                    }
                };

        Thread firstThread = new Thread(runnable, "Thread-1:");
        Thread secondThread = new Thread(runnable, "Thread-2:");

        firstThread.start();
        secondThread.start();
        firstThread.join();
        secondThread.join();

        assertEquals(10, usingAtLocked.counter);
    }

    @Test
    void shouldPrintStatementWithoutException() {

        assertDoesNotThrow(usingAtLocked::printStatement);
    }

    @Test
    void shouldPropagateInterruptedException() {

        currentThread().interrupt();

        Exception e = assertThrows(Exception.class, usingAtLocked::printStatement);
        assertInstanceOf(InterruptedException.class, e);
    }
}
