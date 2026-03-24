package com.teb.practice.utility;

import static java.lang.management.ManagementFactory.getMemoryMXBean;

import java.lang.management.MemoryUsage;

public class JVMMemoryService {

    public MemoryStats getHeapStats() {

        MemoryUsage heap = getMemoryMXBean().getHeapMemoryUsage();

        return new MemoryStats(heap.getInit(), heap.getMax(), heap.getCommitted(), heap.getUsed());
    }

    public MemoryStats getNonHeapStats() {

        MemoryUsage nonHeap = getMemoryMXBean().getNonHeapMemoryUsage();

        return new MemoryStats(
                nonHeap.getInit(), nonHeap.getMax(), nonHeap.getCommitted(), nonHeap.getUsed());
    }
}
