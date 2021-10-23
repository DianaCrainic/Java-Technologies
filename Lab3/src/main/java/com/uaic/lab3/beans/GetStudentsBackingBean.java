package com.uaic.lab3.beans;

import com.uaic.lab3.daos.StudentDao;
import com.uaic.lab3.entities.Student;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

@Named
@SessionScoped
public class GetStudentsBackingBean implements Serializable {
    private final StudentDao studentDao;

    public GetStudentsBackingBean() {
        studentDao = new StudentDao();
    }

    public List<Student> getStudents() throws SQLException {
        return studentDao.getAll();
    }
}
