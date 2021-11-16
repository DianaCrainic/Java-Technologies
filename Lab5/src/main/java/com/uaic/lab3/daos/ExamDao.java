package com.uaic.lab3.daos;

import com.uaic.lab3.entities.Exam;

import java.util.List;

public abstract class ExamDao<T extends Exam> extends Dao<Exam>{
    public abstract List<T> getAll();
}

//public class ExamDao extends Dao<Exam> {
//    public Exam getById(Integer id) {
//        return entityManager.find(Exam.class, id);
//    }
//
//    public List getAll() {
//        return entityManager.createNamedQuery("Exam.getAll").getResultList();
//    }
//
//    public void create(ExamDto examDto) {
//        String name = examDto.getName();
//        Timestamp startingTime = examDto.getStartingTime();
//        Integer duration = examDto.getDuration();
//        Exam exam = new Exam(name, startingTime, duration);
//        beginTransaction();
//        entityManager.persist(exam);
//        entityManager.flush();
//        commitTransaction();
//    }
//
//    public void update(Integer id, ExamDto examDto) {
//        Exam exam = this.getById(id);
//        exam.setName(examDto.getName());
//        exam.setStartingTime(examDto.getStartingTime());
//        exam.setDuration(examDto.getDuration());
//        beginTransaction();
//        entityManager.merge(exam);
//        entityManager.flush();
//        commitTransaction();
//    }
//}
