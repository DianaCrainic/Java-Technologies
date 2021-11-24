package com.uaic.lab3.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "exams")
@NamedQueries({
        @NamedQuery(name = "Exam.getAll", query = "SELECT exam FROM Exam exam")
})
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "exam_type",
        discriminatorType = DiscriminatorType.STRING)
public class Exam extends AbstractEntity{
    @Basic(optional = false)
    @Column(name = "name")
    private String name;

    @Basic(optional = false)
    @Column(name = "starting_time")
    private Timestamp startingTime;

    @Basic(optional = false)
    @Column(name = "duration")
    private Integer duration;

    public Exam(String name, Timestamp startingTime, Integer duration) {
        this.name = name;
        this.startingTime = startingTime;
        this.duration = duration;
    }
}

