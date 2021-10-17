package com.uaic.lab3.entities;

import java.util.Arrays;
import java.util.List;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Student {
    private int id;
    private String name;
    private List<Exam> exams;

    public Student(String name) {
        this.name = name;
    }

    public Student(String name, List<Exam> exams) {
        this.name = name;
        this.exams = exams;
    }

    public void addExam(Exam... exams) {
        this.exams.addAll(Arrays.asList(exams));
    }
}
