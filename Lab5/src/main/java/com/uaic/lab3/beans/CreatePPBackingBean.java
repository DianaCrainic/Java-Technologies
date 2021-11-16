package com.uaic.lab3.beans;

import com.uaic.lab3.daos.ProjectPresentationDao;
import com.uaic.lab3.entities.ProjectPresentation;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.sql.Timestamp;

@Getter
@Setter
@Named
@SessionScoped
public class CreatePPBackingBean extends CreateExamBackingBean {
    private boolean partialEvaluation;

    @PostConstruct
    public void init() {
        examDao = new ProjectPresentationDao();
    }

    @Override
    public void submit() {
        ProjectPresentation projectPresentation = new ProjectPresentation(name, new Timestamp(startingTime.getTime()), duration, partialEvaluation);
        examDao.create(projectPresentation);
    }
}
