package com.uaic.lab3.beans;

import com.uaic.lab3.daos.WrittenTestDao;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.util.List;

@Named
@ViewScoped
public class DataWTViewBackingBean extends DataExamsViewBackingBean {
    @EJB
    protected WrittenTestDao examDao;

    @PostConstruct
    public void init() {
        entities = examDao.getAll(dateFilters);
    }

    public List getEntities() {
        entities = examDao.getAll(dateFilters);
        return entities;
    }
}