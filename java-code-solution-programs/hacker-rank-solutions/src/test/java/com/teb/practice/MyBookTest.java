package com.teb.practice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class MyBookTest {

    private static final String TITLE = "Dreams Made of Nightmares";
    private static final String AUTHOR = "Joe Williamson";
    private static final double PRICE = 20.0;

    @Test
    void testMyBook() {

        Book book = new MyBook(TITLE, AUTHOR, PRICE);

        assertEquals(TITLE, book.title);
        assertEquals(AUTHOR, book.author);
        assertEquals(PRICE, book.getPrice());
    }
}
