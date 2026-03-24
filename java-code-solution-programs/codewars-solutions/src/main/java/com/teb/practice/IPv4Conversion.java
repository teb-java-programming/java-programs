package com.teb.practice;

/*
 * Complete the function that takes an unsigned 32-bit number and returns a string representation of its IPv4 address.
 */

public class IPv4Conversion {

    protected long ipToNumber(String ipAddress) {

        long result = 0;
        String[] ipOctets = ipAddress.split("\\.");

        for (int i = 0; i < 4; i++) {
            long octet = Long.parseLong(ipOctets[i]);
            result |= (octet << (24 - (8 * i)));
        }

        return result;
    }

    protected String numberToIp(long number) {

        return String.format(
                "%d.%d.%d.%d",
                (number >> 24) & 0xFF, (number >> 16) & 0xFF, (number >> 8) & 0xFF, number & 0xFF);
    }
}
