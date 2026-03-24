package com.teb.practice.utility;

import static java.lang.System.getProperty;
import static java.lang.System.out;
import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;
import static java.time.LocalDateTime.now;
import static java.time.format.DateTimeFormatter.ofPattern;

public class HandleJVMSpace {

    public static void main(String[] args) {

        JVMMemoryService service = new JVMMemoryService();

        while (!currentThread().isInterrupted()) {
            clearConsole();

            out.println("\n\n==============================");
            out.println("==== JVM MEMORY DASHBOARD ====");
            out.println("==== " + now().format(ofPattern("dd-MMM-yyyy HH:mm:ss")) + " ====");

            out.println("\n---- HEAP ----");
            out.println(service.getHeapStats());

            out.println("\n---- NON-HEAP ----");
            out.println(service.getNonHeapStats());

            pause();
        }
    }

    private static void pause() {

        try {
            sleep(2000);
        } catch (InterruptedException e) {
            currentThread().interrupt();
        }
    }

    private static void clearConsole() {

        try {
            String os = getProperty("os.name");

            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                out.print("\033[H\033[2J");
                out.flush();
            }
        } catch (Exception ignored) {
            out.println("\n".repeat(50));
        }
    }
}
