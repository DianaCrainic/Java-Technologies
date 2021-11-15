package com.uaic.lab3.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student extends AbstractEntity<Integer> {
    private String name;
    private String assignedExams;

    public Student(Integer id, String name, String assignedExams) {
        super(id);
        this.name = name;
        this.assignedExams = assignedExams;
    }
}

