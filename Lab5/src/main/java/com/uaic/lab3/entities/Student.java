package com.uaic.lab3.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "students")
@NamedQueries({
        @NamedQuery(name = "Student.getAll", query = "SELECT student FROM Student student")
})
public class Student extends AbstractEntity implements Serializable {
    @Basic(optional = false)
    @Column(name = "name")
    private String name;

    @Basic(optional = false)
    @Column(name = "assigned_exams")
    private String assignedExams;

    public Student(String name, String assignedExams) {
        this.name = name;
        this.assignedExams = assignedExams;
    }
}

