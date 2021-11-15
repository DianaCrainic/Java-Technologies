package com.uaic.lab3.beans;

import com.uaic.lab3.daos.ExamDao;
import com.uaic.lab3.entities.Exam;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.naming.NamingException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

@Named
@SessionScoped
public class DataExamsViewBackingBean extends DataViewBackingBean<Exam, Integer> {
    private final ExamDao examDao;

    public DataExamsViewBackingBean() throws SQLException, NamingException {
        examDao = new ExamDao();
        entities = examDao.getAll();
    }

    public List<Exam> getExams() throws SQLException {
        entities = examDao.getAll();
        return entities;
    }

    public String getEditExamUrl(Integer id) {
        return String.format("/edit-exams.xhtml?exam_id=%s", id.toString());
    }
}
