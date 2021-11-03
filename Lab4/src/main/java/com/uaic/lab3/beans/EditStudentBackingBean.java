package com.uaic.lab3.beans;

import com.uaic.lab3.daos.StudentDao;
import com.uaic.lab3.entities.Student;
import lombok.Getter;
import lombok.Setter;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.naming.NamingException;
import java.io.Serializable;
import java.sql.SQLException;

@Getter
@Setter
@Named
@ViewScoped
public class EditStudentBackingBean implements Serializable {
    private final StudentDao studentDao;
    private Student student;

    public EditStudentBackingBean() throws SQLException, NamingException {
        studentDao = new StudentDao();
        Integer id =
                Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("student_id"));
        student = studentDao.getById(id);
    }

    public void submit() throws SQLException {
        studentDao.update(student);
    }
}
