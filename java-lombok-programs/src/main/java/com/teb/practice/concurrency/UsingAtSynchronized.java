package com.teb.practice.concurrency;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

import lombok.SneakyThrows;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UsingAtSynchronized {

    int counter = 0;
    double balance = 0;

    // @Synchronized makes the methods share a lock and are run sequentially
    @Synchronized
    void deposit(double amount) {

        balance += amount;
        counter++;

        log.info("{} Balance: £{}", currentThread().getName(), balance);
    }

    // @SneakyThrows invokes checked exceptions without explicit try-catch
    @SneakyThrows
    @Synchronized
    void printStatement() {

        sleep(200);

        log.info("{} Statement printed", currentThread().getName());
    }
}
