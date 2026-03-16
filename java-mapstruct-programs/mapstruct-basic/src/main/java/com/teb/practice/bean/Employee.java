package com.teb.practice.bean;

import lombok.Data;

import java.util.Date;

@Data
public class Employee {

    private String id;
    private String name;
    private Date startDate;
    private Project project;
}
