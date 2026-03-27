package com.teb.practice.mapper;

import static com.teb.practice.bean.EmployeeType.CONTRACTOR;
import static com.teb.practice.bean.EmployeeType.INTERN;
import static com.teb.practice.bean.EmployeeType.PERMANENT;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.teb.practice.bean.Contractor;
import com.teb.practice.bean.Employee;
import com.teb.practice.bean.EmployeeType;
import com.teb.practice.bean.Intern;
import com.teb.practice.bean.Permanent;
import com.teb.practice.bean.Project;
import com.teb.practice.dto.EmployeeDto;
import com.teb.practice.dto.ProjectDto;

import lombok.SneakyThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mapstruct.factory.Mappers;

import java.text.SimpleDateFormat;
import java.util.stream.Stream;

class MapperTest {

    private static final EmployeeMapper EMPLOYEE_MAPPER = Mappers.getMapper(EmployeeMapper.class);
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    private static final String EMPLOYEE_START_DATE = "16-04-2024 10:00:00";
    private static final String PROJECT_START_DATE = "08-08-2024 08:00:00";

    static Stream<Arguments> employeeProvider() {

        return Stream.of(
                Arguments.of("P001", "E001", new Employee(), null),
                Arguments.of(null, "E002", new Permanent(), PERMANENT),
                Arguments.of("P002", "E003", new Contractor(), CONTRACTOR),
                Arguments.of("P003", null, new Intern(), INTERN),
                Arguments.of(null, null, new Employee(), null));
    }

    @SneakyThrows
    @ParameterizedTest
    @MethodSource("employeeProvider")
    void testMapEmployeeToEmployeeDto(
            String projectId, String employeeId, Employee employee, EmployeeType employeeType) {

        Project project = new Project();
        project.setId(projectId);
        project.setName("Space Launch");
        project.setStartDate(DATE_FORMAT.parse(PROJECT_START_DATE));

        employee.setId(employeeId);
        employee.setName("Austin");
        employee.setStartDate(DATE_FORMAT.parse(EMPLOYEE_START_DATE));
        employee.setProject(project);

        EmployeeDto employeeDto = EMPLOYEE_MAPPER.employeeToEmployeeDto(employee);

        assertNotNull(employeeDto);
        if (employeeId != null) assertEquals(employeeId, employeeDto.getEmployeeId());
        else assertNotNull(employeeDto.getEmployeeId());

        assertEquals(employee.getName(), employeeDto.getEmployeeName());
        assertEquals(
                DATE_FORMAT.format(employee.getStartDate()), employeeDto.getEmployeeStartDate());

        assertNotNull(employeeDto.getProjectDto());
        if (projectId != null) assertEquals(projectId, employeeDto.getProjectDto().getProjectId());
        else assertNotNull(employeeDto.getProjectDto().getProjectId());

        assertNotNull(employeeDto.getProjectDto().getProjectId());
        assertEquals(employee.getProject().getName(), employeeDto.getProjectDto().getProjectName());
        assertEquals(
                DATE_FORMAT.format(employee.getProject().getStartDate()),
                employeeDto.getProjectDto().getProjectStartDate());

        assertEquals(employeeType, employeeDto.getEmployeeType());
    }

    @SneakyThrows
    @Test
    void testMapEmployeeDtoToEmployee() {

        ProjectDto projectDto = new ProjectDto();
        projectDto.setProjectId("P004");
        projectDto.setProjectName("Jupiter Landing");
        projectDto.setProjectStartDate(PROJECT_START_DATE);

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setEmployeeId("E004");
        employeeDto.setEmployeeName("John");
        employeeDto.setEmployeeStartDate(EMPLOYEE_START_DATE);
        employeeDto.setProjectDto(projectDto);

        Employee employee = EMPLOYEE_MAPPER.employeeDtotoEmployee(employeeDto);

        assertNotNull(employee);
        assertEquals(employeeDto.getEmployeeId(), employee.getId());
        assertEquals(employeeDto.getEmployeeName(), employee.getName());
        assertEquals(
                DATE_FORMAT.parse(employeeDto.getEmployeeStartDate()), employee.getStartDate());

        assertNotNull(employee.getProject());
        assertEquals(employeeDto.getProjectDto().getProjectId(), employee.getProject().getId());
        assertEquals(employeeDto.getProjectDto().getProjectName(), employee.getProject().getName());
        assertEquals(
                DATE_FORMAT.parse(employeeDto.getProjectDto().getProjectStartDate()),
                employee.getProject().getStartDate());
    }
}
