package com.uaic.lab3.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Exam {
    private Integer id;
    private String name;
    private Timestamp startingTime;
    private Integer duration;
}
