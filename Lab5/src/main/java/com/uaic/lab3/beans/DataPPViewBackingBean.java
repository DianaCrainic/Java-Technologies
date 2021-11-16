package com.uaic.lab3.beans;

import com.uaic.lab3.daos.ProjectPresentationDao;
import com.uaic.lab3.entities.ProjectPresentation;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class DataPPViewBackingBean extends DataExamsViewBackingBean<ProjectPresentation> {
    @PostConstruct
    public void init() {
        examDao = new ProjectPresentationDao();
        entities = examDao.getAll();
    }
}
