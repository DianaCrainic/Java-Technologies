package com.uaic.lab3.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class StudentDto {
    private String name;
    private String assignedExams;
}
