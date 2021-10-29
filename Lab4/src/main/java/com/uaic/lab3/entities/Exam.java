package com.uaic.lab3.entities;

import lombok.Getter;
import lombok.Setter;
import java.sql.Timestamp;

@Getter
@Setter
public class Exam extends AbstractEntity<Integer> {
    private String name;
    private Timestamp startingTime;
    private Integer duration;

    public Exam(Integer id, String name, Timestamp startingTime, Integer duration) {
        super(id);
        this.name = name;
        this.startingTime = startingTime;
        this.duration = duration;
    }
}

