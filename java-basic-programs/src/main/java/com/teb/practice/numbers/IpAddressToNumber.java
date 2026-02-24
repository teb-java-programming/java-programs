package com.teb.practice.numbers;

import static com.teb.practice.constants.Constants.SCAN;

import static java.lang.System.out;

public class IpAddressToNumber {

    private static long ipToNumber(String ipAddress) {

        long result = 0;
        String[] ipOctets = ipAddress.split("\\.");

        for (int i = 0; i < 4; i++) {
            long octet = Long.parseLong(ipOctets[i]);
            result |= (octet << (24 - (8 * i)));
        }

        return result;
    }

    private static String numberToIp(long number) {

        return String.format(
                "%d.%d.%d.%d",
                (number >> 24) & 0xFF, (number >> 16) & 0xFF, (number >> 8) & 0xFF, number & 0xFF);
    }

    public static void main(String[] args) {

        out.println("Select your conversion task");
        out.println("1. IPv4 to number");
        out.println("2. Number to IPv4");
        out.print("Your choice: ");
        int choice = SCAN.nextInt();

        switch (choice) {
            case 1:
                out.print("Enter IPv4 address (0.0.0.0): ");
                String input = SCAN.next();

                out.println("Corresponding number is: " + ipToNumber(input));
                break;
            case 2:
                out.print("Enter number: ");
                long number = SCAN.nextLong();

                out.println("Corresponding IPv4 address (0.0.0.0) is: " + numberToIp(number));
                break;
            default:
                out.println("Invalid option!");
        }
    }
}
