package com.uaic.lab3.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@Entity
@DiscriminatorValue("written test")
@NamedQueries({
        @NamedQuery(name = "WrittenTest.getAll", query = "SELECT exam FROM WrittenTest exam")
})
public class WrittenTest extends Exam {
    @Column(name = "bibliography")
    private String bibliography;

    public WrittenTest(String name, Timestamp startingTime, int duration, String bibliography) {
        super(name, startingTime, duration);
        this.bibliography = bibliography;
    }
}
