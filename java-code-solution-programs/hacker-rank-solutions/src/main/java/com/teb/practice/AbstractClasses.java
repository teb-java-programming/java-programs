package com.teb.practice;

/*
 * Given a Book class and a Solution class, write a MyBook class that does the following:
 * - Inherits from Book
 * - Has a parameterized constructor taking these 3 parameters:
 *  1. string title
 *  2. string author
 *  3. int price
 * - Implements the Book class' abstract display() method so it prints these 3 lines:
 *  1. Title:, a space, and then the current instance's title.
 *  2. Author:, a space, and then the current instance's author.
 *  3. Price:, a space, and then the current instance's price.
 */

import static com.teb.practice.constants.Constants.SCAN;

import static java.lang.System.out;

abstract class Book {

    String title;
    String author;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    abstract void display();
}

class MyBook extends Book {

    int price;

    public MyBook(String title, String author, int price) {
        super(title, author);
        this.price = price;
    }

    @Override
    void display() {

        out.println("Title: " + title);
        out.println("Author: " + author);
        out.println("Price: " + price);
    }
}

public class AbstractClasses {

    public static void main(String[] args) {

        out.println("Enter book details:");
        out.print("Title: ");
        String title = SCAN.nextLine();
        out.print("Author: ");
        String author = SCAN.nextLine();
        out.print("Price: ");
        int price = SCAN.nextInt();

        Book book = new MyBook(title, author, price);
        book.display();
    }
}
