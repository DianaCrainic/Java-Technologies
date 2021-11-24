package com.uaic.lab3.daos;

import com.uaic.lab3.entities.Exam;
import com.uaic.lab3.filters.DateFilters;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class ExamDao extends Dao<Exam> {
    public List<Exam> getAll(DateFilters dateFilters){
        return entityManager.createNamedQuery("Exam.getAll").getResultList();
    }

    public Exam getById(Integer id) {
        return entityManager.find(Exam.class, id);
    }
}
