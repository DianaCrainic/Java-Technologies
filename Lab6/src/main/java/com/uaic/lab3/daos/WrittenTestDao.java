package com.uaic.lab3.daos;

import com.uaic.lab3.entities.WrittenTest;
import com.uaic.lab3.filters.DateFilters;

import java.util.List;

public class WrittenTestDao extends ExamDao<WrittenTest> {
    @Override
    public List<WrittenTest> getAll(DateFilters dateFilters) {
        return entityManager.createNamedQuery("WrittenTest.getAll").getResultList();
    }
}
