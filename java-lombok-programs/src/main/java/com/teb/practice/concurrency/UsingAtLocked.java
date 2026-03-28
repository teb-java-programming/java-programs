package com.teb.practice.concurrency;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

import lombok.Locked;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

// @Slf4j invokes an instance of slf4j log
@Slf4j
public class UsingAtLocked {

    private final ReentrantLock depositLock = new ReentrantLock();
    private final ReentrantLock statementLock = new ReentrantLock();

    int counter = 0;
    double balance = 0;

    // @Locked triggers granular locks allowing individual lock on a method
    @Locked("depositLock")
    void deposit(double amount) {

        balance += amount;
        counter++;

        log.info("{} Balance: £{}", currentThread().getName(), balance);
    }

    // @SneakyThrows invokes checked exceptions without explicit try-catch
    @SneakyThrows
    @Locked("statementLock")
    void printStatement() {

        sleep(200);

        log.info("{} Statement printed", currentThread().getName());
    }
}
