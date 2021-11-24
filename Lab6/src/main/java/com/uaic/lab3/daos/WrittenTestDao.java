package com.uaic.lab3.daos;

import com.uaic.lab3.entities.WrittenTest;
import com.uaic.lab3.filters.DateFilters;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class WrittenTestDao extends Dao<WrittenTest> {
    public List<WrittenTest> getAll(DateFilters dateFilters) {
        return entityManager.createNamedQuery("WrittenTest.getAll").getResultList();
    }
}
