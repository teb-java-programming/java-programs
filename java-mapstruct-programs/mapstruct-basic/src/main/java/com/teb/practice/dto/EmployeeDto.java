package com.teb.practice.dto;

import com.teb.practice.bean.EmployeeType;
import lombok.Data;

@Data
public class EmployeeDto {

    private String employeeId;
    private String employeeName;
    private String employeeStartDate;
    private EmployeeType employeeType;
    private ProjectDto projectDto;
}
