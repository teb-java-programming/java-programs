package com.teb.practice;

/*
 * HackerLand University has the following grading policy:
 * Every student receives a grade in the inclusive range from 0 to 100.
 * Any grade less than 40 is a failing grade.
 * Sam is a professor at the university and likes to round each student's grade according to these rules:
 * If the difference between the grade and the next multiple of 5 is less than 3, round grade up to the next multiple of 5.
 * If the value of grade is less than 38, no rounding occurs as the result will still be a failing grade.
 */

import static com.teb.practice.constants.Constants.SCAN;

import static java.lang.System.out;

import java.util.ArrayList;
import java.util.List;

public class GradingStudents {

    static List<Integer> gradingStudents(List<Integer> grades) {

        List<Integer> finalGrades = new ArrayList<>();

        for (int grade : grades) {
            if (grade < 38 || grade % 5 <= 2) {
                finalGrades.add(grade);
            } else {
                grade += (5 - (grade % 5));
                finalGrades.add(grade);
            }
        }

        return finalGrades;
    }

    public static void main(String[] args) {

        List<Integer> inputList = new ArrayList<>();

        out.print("Enter the number of students: ");
        int studentCount = SCAN.nextInt();

        while (studentCount-- > 0) {
            inputList.add(SCAN.nextInt());
        }

        out.println("Final grades: " + gradingStudents(inputList));
    }
}
