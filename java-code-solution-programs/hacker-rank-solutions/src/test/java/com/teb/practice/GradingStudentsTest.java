package com.teb.practice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.util.List;

class GradingStudentsTest {

    private final GradingStudents gradingStudents = new GradingStudents();

    @Test
    void gradingStudents() {

        List<Integer> gradesList = List.of(19, 42, 99, 74, 66, 38);

        assertEquals(List.of(19, 42, 100, 75, 66, 40), gradingStudents.grades(gradesList));
    }
}
