package com.uaic.lab3.beans;

import com.uaic.lab3.daos.ExamDao;
import com.uaic.lab3.entities.Exam;
import com.uaic.lab3.filters.DateFilters;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DataExamsViewBackingBean<T extends Exam> extends DataViewBackingBean<Exam> {
    protected DateFilters dateFilters = new DateFilters();
    protected ExamDao<T> examDao;

    public List getEntities() {
        entities = examDao.getAll(dateFilters);
        return entities;
    }
}
