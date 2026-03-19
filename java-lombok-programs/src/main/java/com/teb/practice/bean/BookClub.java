package com.teb.practice.bean;

import lombok.Value;

import java.util.List;

// @Value invokes @AllArgsConstructor and all fields are made private and final
@Value
public class BookClub {
    String bookClubName;
    String bookClubAddress;
    List<Book> books;
    List<Volunteer> volunteers;
}
