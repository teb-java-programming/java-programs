package com.teb.practice.concurrency;

import static java.lang.Thread.currentThread;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import lombok.SneakyThrows;

import org.junit.jupiter.api.Test;

class UsingAtSynchronizedTest {

    private final UsingAtSynchronized usingAtSynchronized = new UsingAtSynchronized();

    private double amount = 100;

    @SneakyThrows
    @Test
    void testCounters() {

        Runnable runnable =
                () -> {
                    for (int i = 0; i < 5; i++) {
                        usingAtSynchronized.deposit(amount++);
                        usingAtSynchronized.printStatement();
                    }
                };

        Thread firstThread = new Thread(runnable, "Thread-1:");
        Thread secondThread = new Thread(runnable, "Thread-2:");

        firstThread.start();
        secondThread.start();
        firstThread.join();
        secondThread.join();

        assertEquals(10, usingAtSynchronized.counter);
    }

    @Test
    void shouldPrintStatementWithoutException() {

        assertDoesNotThrow(usingAtSynchronized::printStatement);
    }

    @Test
    void shouldPropagateInterruptedException() {

        currentThread().interrupt();

        Exception e = assertThrows(Exception.class, usingAtSynchronized::printStatement);
        assertInstanceOf(InterruptedException.class, e);
    }
}
