package com.uaic.lab3.entities;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.sql.Date;
import java.time.LocalTime;

import lombok.*;

//@ManagedBean(name = "examBean")
//@RequestScoped
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Exam {
    private int id;
    private String name;
    private Date startingTime;
    private int duration;
}
