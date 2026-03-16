package com.teb.practice.mapper;

import com.teb.practice.bean.Employee;
import com.teb.practice.dto.EmployeeDto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        uses = {AccessMapper.class, SkillMapper.class})
public interface EmployeeMapper {

    @Mapping(
            target = "employeeId",
            source = "id",
            defaultExpression = "java(java.util.UUID.randomUUID().toString())")
    @Mapping(target = "employeeName", source = "name")
    @Mapping(target = "employeeStartDate", source = "startDate", dateFormat = "dd-MM-yyyy HH:mm:ss")
    @Mapping(target = "accessDto", source = "access")
    @Mapping(target = "skills", source = "skills")
    EmployeeDto employeeToEmployeeDto(Employee employee);

    @Mapping(target = "id", source = "employeeId")
    @Mapping(target = "name", source = "employeeName")
    @Mapping(target = "startDate", source = "employeeStartDate", dateFormat = "dd-MM-yyyy HH:mm:ss")
    @Mapping(target = "access", source = "accessDto")
    @Mapping(target = "skills", source = "skills")
    Employee employeeDtotoEmployee(EmployeeDto employeeDto);
}
