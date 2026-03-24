package com.teb.practice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class IPv4ConversionTest {

    private static final String IPV4_ADDRESS = "128.0.64.32";
    private static final long LONG_ADDRESS = 2147500064L;

    private final IPv4Conversion iPv4Conversion = new IPv4Conversion();

    @Test
    void testIPv4ToNumberConversion() {

        assertEquals(LONG_ADDRESS, iPv4Conversion.ipToNumber(IPV4_ADDRESS));
    }

    @Test
    void testNumberToIPv4Conversion() {

        assertEquals(IPV4_ADDRESS, iPv4Conversion.numberToIp(LONG_ADDRESS));
    }
}
