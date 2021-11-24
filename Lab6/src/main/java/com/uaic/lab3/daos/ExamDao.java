package com.uaic.lab3.daos;

import com.uaic.lab3.entities.Exam;
import com.uaic.lab3.filters.DateFilters;

import java.util.List;

public abstract class ExamDao<T extends Exam> extends Dao<Exam> {
    public abstract List<T> getAll(DateFilters dateFilters);
}
