package com.uaic.lab3.beans;

import com.uaic.lab3.daos.ExamDao;
import com.uaic.lab3.entities.Exam;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.util.List;

@Named
@SessionScoped
public class DataExamsViewBackingBean extends DataViewBackingBean<Exam> {
    private ExamDao examDao;

    @PostConstruct
    public void init() {
        examDao = new ExamDao();
        entities = examDao.getAll();
    }

    public List getEntities() {
        entities = examDao.getAll();
        return entities;
    }

    public String getEditExamUrl(Integer id) {
        return String.format("/edit-exams.xhtml?exam_id=%s", id.toString());
    }
}
