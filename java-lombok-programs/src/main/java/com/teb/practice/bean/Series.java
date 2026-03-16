package com.teb.practice.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.With;

@Getter
@AllArgsConstructor
public class Series {
    Book book;
    // @With allows cloning of an object and with one changed field
    @With private int edition;
}
