package com.uaic.lab3.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class ExamDto {
    private String name;
    private Timestamp startingTime;
    private Integer duration;
}
