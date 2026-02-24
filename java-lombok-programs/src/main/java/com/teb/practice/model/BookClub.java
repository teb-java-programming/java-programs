package com.teb.practice.model;

import lombok.Value;

// @Value - invokes @AllArgsConstructor and all fields are made private and final
@Value
public class BookClub {
    String bookClubName;
    String bookClubAddress;
    Book book;
    Volunteer volunteer;
}
