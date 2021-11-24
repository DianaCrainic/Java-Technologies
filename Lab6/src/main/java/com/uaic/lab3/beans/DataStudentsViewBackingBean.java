package com.uaic.lab3.beans;

import com.uaic.lab3.daos.StudentDao;
import com.uaic.lab3.entities.Student;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.util.List;

@Named
@ViewScoped
public class DataStudentsViewBackingBean extends DataViewBackingBean<Student> {
    @EJB
    private StudentDao studentDao;

    @PostConstruct
    public void init() {
        entities = studentDao.getAll();
    }

    public List<Student> getEntities() {
        entities = studentDao.getAll();
        return entities;
    }

    public String getEditStudentUrl(Integer id) {
        return String.format("/edit-students.xhtml?student_id=%s", id.toString());
    }
}