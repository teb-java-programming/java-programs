package com.teb.practice.utility;

public record MemoryStats(long init, long max, long committed, long used) {

    public long usedPercent() {
        return (max > 0) ? (used * 100 / max) : 0;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public String toString() {

        return String.format(
                "Init: %d MB | Max: %d MB | Committed: %d MB | Used: %d MB (%d%%)",
                toMegabytes(init),
                toMegabytes(max),
                toMegabytes(committed),
                toMegabytes(used),
                usedPercent());
    }

    private long toMegabytes(long bytes) {

        return bytes / (1024 * 1024);
    }
}
