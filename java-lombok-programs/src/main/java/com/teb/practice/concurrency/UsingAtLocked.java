package com.teb.practice.concurrency;

import static java.lang.System.out;
import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

import lombok.Locked;
import lombok.SneakyThrows;

import java.util.concurrent.locks.ReentrantLock;

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
        out.println(currentThread().getName() + " Balance: £" + balance);
    }

    // @SneakyThrows invokes checked exceptions without explicit try-catch
    @SneakyThrows
    @Locked("statementLock")
    void printStatement() {

        sleep(200);
        out.println(currentThread().getName() + " Statement printed");
    }
}
