package com.uaic.lab3.beans;

import com.uaic.lab3.daos.StudentDao;
import com.uaic.lab3.entities.Student;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.sql.SQLException;
import java.util.List;

@Named
@ViewScoped
public class DataStudentsViewBackingBean extends DataViewBackingBean<Student, Integer> {
    private final StudentDao studentDao;

    public DataStudentsViewBackingBean() throws SQLException {
        studentDao = new StudentDao();
        entities = studentDao.getAll();
    }

    public List<Student> getEntities() throws SQLException {
        entities = studentDao.getAll();
        return entities;
    }

    public String getEditStudentUrl(Integer id) {
        return String.format("/edit-students.xhtml?student_id=%s", id.toString());
    }
}