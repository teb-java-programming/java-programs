package com.teb.practice.bean;

import static java.time.LocalDateTime.now;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;

// @Builder allows a class to be instantiable without a constructor or setter call
@Builder
@Getter
@Setter
public class Volunteer {
    private String volunteerName;
    private int age;
    // @Builder.Default enables assigning a default value to a field accessible by Lombok
    // constructors
    @Builder.Default private LocalDateTime signupDate = now();
    // @Singular provides option to assign a single value or a list
    @Singular private List<String> workExperiences;
}
