package com.teb.practice.utility;

import static java.lang.System.out;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;

public class HandleJVMSpace {

    private static void getRuntimeData() {

        // Total memory available to JVM
        out.println(Runtime.getRuntime().totalMemory());

        // Maximum memory JVM tries to use
        out.println(Runtime.getRuntime().maxMemory());

        // Free memory available to JVM
        out.println(Runtime.getRuntime().freeMemory());

        // Garbage collection
        Runtime.getRuntime().gc();
    }

    private static void getMXHeapData() {

        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();

        // Total memory available to JVM
        out.println(memoryBean.getHeapMemoryUsage().getInit());

        // Maximum memory JVM tries to use
        out.println(memoryBean.getHeapMemoryUsage().getMax());

        // Guaranteed memory available to JVM
        out.println(memoryBean.getHeapMemoryUsage().getCommitted());

        // Memory used by JVM
        out.println(memoryBean.getHeapMemoryUsage().getUsed());
    }

    private static void getMXNonHeapData() {

        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();

        // Total memory available to JVM
        out.println(memoryBean.getNonHeapMemoryUsage().getInit());

        // Maximum memory JVM tries to use
        out.println(memoryBean.getNonHeapMemoryUsage().getMax());

        // Guaranteed memory available to JVM
        out.println(memoryBean.getNonHeapMemoryUsage().getCommitted());

        // Memory used by JVM
        out.println(memoryBean.getNonHeapMemoryUsage().getUsed());
    }

    public static void main(String[] args) {

        out.println("\nJVM heap space details using Runtime class");
        getRuntimeData();

        out.println("\nJVM heap space details using MemoryMXBean class");
        getMXHeapData();

        out.println("\nJVM non-heap space details using MemoryMXBean class");
        getMXNonHeapData();
    }
}
