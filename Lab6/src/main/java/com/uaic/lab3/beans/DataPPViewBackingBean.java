package com.uaic.lab3.beans;

import com.uaic.lab3.daos.ProjectPresentationDao;
import com.uaic.lab3.entities.ProjectPresentation;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named
@ApplicationScoped
public class DataPPViewBackingBean extends DataExamsViewBackingBean<ProjectPresentation> {
    @PostConstruct
    public void init() {
        examDao = new ProjectPresentationDao();
        entities = examDao.getAll(dateFilters);
    }
}
