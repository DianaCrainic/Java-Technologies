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
@DiscriminatorValue("project presentation")
@NamedQueries({
        @NamedQuery(name = "ProjectPresentation.getAll", query = "SELECT exam FROM ProjectPresentation exam")
})
public class ProjectPresentation extends Exam {
    @Column(name = "partial_evaluation")
    private boolean partialEvaluation;

    public ProjectPresentation(String name, Timestamp startingTime, Integer duration, boolean partialEvaluation) {
        super(name, startingTime, duration);
        this.partialEvaluation = partialEvaluation;
    }
}
