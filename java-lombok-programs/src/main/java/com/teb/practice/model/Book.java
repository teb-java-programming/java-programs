package com.teb.practice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

// @Getter - invokes getters for all fields in the class
// @Setter - invokes setters for all fields in the class
// @NoArgsConstructor - invokes default constructor
// @AllArgsConstructor - invokes parameterised constructor for all fields
// @RequiredArgsConstructor - invokes constructor for all non-null fields
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Book {
    @NonNull private String bookId;
    private String bookName;
    private Author author;
}
