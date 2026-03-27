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

abstract class Book {

    String title;
    String author;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    abstract double getPrice();
}

public class MyBook extends Book {

    double price;

    public MyBook(String title, String author, double price) {
        super(title, author);
        this.price = price;
    }

    @Override
    double getPrice() {

        return price;
    }
}
