package com.teb.practice.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import static java.time.LocalDateTime.parse;

import com.teb.practice.bean.Access;
import com.teb.practice.bean.Employee;
import com.teb.practice.bean.Skill;
import com.teb.practice.dto.AccessDto;
import com.teb.practice.dto.EmployeeDto;
import com.teb.practice.dto.SkillDto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@SpringBootTest(classes = MapperTestConfiguration.class)
class MapperTest {

    private static final DateTimeFormatter DATE_FORMAT =
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    @Autowired private EmployeeMapper employeeMapper;

    @ParameterizedTest
    @CsvSource({
        "E001, Austin, 16-04-2024 10:40:00, Austin316, pass1234word",
        ", Shawn, 28-08-2024 14:20:00, HBK, swee7ch!n"
    })
    void testMapEmployeeToEmployeeDto(
            String employeeId,
            String employeeName,
            String employeeStartDate,
            String accessUser,
            String accessPass) {

        List<Skill> skills = List.of(new Skill("Java", "8"), new Skill("Spring Boot", "4"));

        LocalDateTime startDate = parse(employeeStartDate, DATE_FORMAT);
        Employee employee =
                new Employee(
                        employeeId,
                        employeeName,
                        startDate,
                        new Access(accessUser, accessPass),
                        skills);

        EmployeeDto employeeDto = employeeMapper.employeeToEmployeeDto(employee);

        assertNotNull(employeeDto);
        if (employeeId != null) assertEquals(employeeId, employeeDto.employeeId());
        else assertNotNull(employeeDto.employeeId());

        assertEquals(employeeName, employeeDto.employeeName());
        assertEquals(startDate.format(DATE_FORMAT), employeeDto.employeeStartDate());

        assertEquals(employee.access().user(), employeeDto.accessDto().username());

        assertEquals(2, employee.skills().size());
        assertEquals("Java", employee.skills().getFirst().name());
        assertEquals("4", employee.skills().getLast().experienceInYears());
    }

    @Test
    void testMapEmployeeDtoToEmployee() {

        List<SkillDto> skillsDto =
                List.of(new SkillDto("Blogging", "12"), new SkillDto("Photography", "8"));

        EmployeeDto employeeDto =
                new EmployeeDto(
                        "E004",
                        "John",
                        "08-08-2024 08:00:00",
                        new AccessDto("JohnCena"),
                        skillsDto);

        Employee employee = employeeMapper.employeeDtotoEmployee(employeeDto);

        assertNotNull(employee);
        assertEquals(employeeDto.employeeId(), employee.id());
        assertEquals(employeeDto.employeeName(), employee.name());
        assertEquals(parse(employeeDto.employeeStartDate(), DATE_FORMAT), employee.startDate());

        assertEquals(employeeDto.accessDto().username(), employee.access().user());
        assertNull(employee.access().pass());

        assertEquals(2, employee.skills().size());
        assertEquals("Blogging", employee.skills().getFirst().name());
        assertEquals("8", employee.skills().getLast().experienceInYears());
    }
}
