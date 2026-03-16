package com.teb.practice.dto;

import java.util.List;

public record EmployeeDto(
        String employeeId,
        String employeeName,
        String employeeStartDate,
        AccessDto accessDto,
        List<SkillDto> skills) {}
