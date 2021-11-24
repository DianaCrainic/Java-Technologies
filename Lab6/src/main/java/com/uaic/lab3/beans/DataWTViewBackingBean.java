package com.uaic.lab3.beans;

import com.uaic.lab3.daos.WrittenTestDao;
import com.uaic.lab3.entities.WrittenTest;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class DataWTViewBackingBean extends DataExamsViewBackingBean<WrittenTest> {
    @PostConstruct
    public void init() {
        examDao = new WrittenTestDao();
        entities = examDao.getAll(dateFilters);
    }
}