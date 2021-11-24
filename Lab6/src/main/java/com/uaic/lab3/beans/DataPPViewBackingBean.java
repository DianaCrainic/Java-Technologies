package com.uaic.lab3.beans;

import com.uaic.lab3.daos.ProjectPresentationDao;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.List;

@Named
@ApplicationScoped
public class DataPPViewBackingBean extends DataExamsViewBackingBean {
    @EJB
    protected ProjectPresentationDao examDao;

    @PostConstruct
    public void init() {
        entities = examDao.getAll(dateFilters);
    }

    public List getEntities() {
        entities = examDao.getAll(dateFilters);
        return entities;
    }
}
