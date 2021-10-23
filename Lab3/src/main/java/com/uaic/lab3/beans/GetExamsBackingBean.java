package com.uaic.lab3.beans;

import com.uaic.lab3.daos.ExamDao;
import com.uaic.lab3.entities.Exam;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

@Named
@SessionScoped
public class GetExamsBackingBean implements Serializable {
    private final ExamDao examDao;

    public GetExamsBackingBean() {
        examDao = new ExamDao();
    }

    public List<Exam> getExams() throws SQLException {
        return examDao.getAll();
    }
}
