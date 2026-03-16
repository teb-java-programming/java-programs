package com.teb.practice.mapper;

import static com.teb.practice.bean.EmployeeType.CONTRACTOR;
import static com.teb.practice.bean.EmployeeType.INTERN;
import static com.teb.practice.bean.EmployeeType.PERMANENT;

import com.teb.practice.bean.Contractor;
import com.teb.practice.bean.Employee;
import com.teb.practice.bean.Intern;
import com.teb.practice.bean.Permanent;
import com.teb.practice.dto.EmployeeDto;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(uses = ProjectMapper.class)
public interface EmployeeMapper {

    @Mapping(
            target = "employeeId",
            source = "id",
            defaultExpression = "java(java.util.UUID.randomUUID().toString())")
    @Mapping(target = "employeeName", source = "name")
    @Mapping(target = "employeeStartDate", source = "startDate", dateFormat = "dd-MM-yyyy HH:mm:ss")
    @Mapping(target = "employeeType", ignore = true)
    @Mapping(target = "projectDto", source = "project")
    EmployeeDto employeeToEmployeeDto(Employee employee);

    @Mapping(target = "id", source = "employeeId")
    @Mapping(target = "name", source = "employeeName")
    @Mapping(target = "startDate", source = "employeeStartDate", dateFormat = "dd-MM-yyyy HH:mm:ss")
    @Mapping(target = "project", source = "projectDto")
    Employee employeeDtotoEmployee(EmployeeDto employeeDto);

    @AfterMapping
    default void setEmployeeType(Employee employee, @MappingTarget EmployeeDto employeeDto) {

        if (employee instanceof Permanent) employeeDto.setEmployeeType(PERMANENT);
        if (employee instanceof Contractor) employeeDto.setEmployeeType(CONTRACTOR);
        if (employee instanceof Intern) employeeDto.setEmployeeType(INTERN);
    }
}
