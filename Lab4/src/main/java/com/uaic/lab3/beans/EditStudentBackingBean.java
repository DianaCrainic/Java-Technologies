package com.uaic.lab3.beans;

import com.uaic.lab3.daos.StudentDao;
import com.uaic.lab3.entities.Student;
import lombok.Getter;
import lombok.Setter;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;

@Getter
@Setter
@Named
@SessionScoped
public class EditStudentBackingBean implements Serializable {
    private final StudentDao studentDao;
    private Student student;

    public EditStudentBackingBean() throws SQLException {
        studentDao = new StudentDao();
        Integer id =
                Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("student_id"));
        student = studentDao.getById(id);
    }

    public void submit() throws SQLException {
        studentDao.update(student);
    }
}
