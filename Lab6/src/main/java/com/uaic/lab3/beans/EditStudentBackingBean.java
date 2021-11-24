package com.uaic.lab3.beans;

import com.uaic.lab3.daos.StudentDao;
import com.uaic.lab3.dtos.StudentDto;
import com.uaic.lab3.entities.Student;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@Getter
@Setter
@Named
@ViewScoped
public class EditStudentBackingBean implements Serializable {
    private Integer id;
    @Getter
    @Setter
    private StudentDto studentDto;
    private StudentDao studentDao;

    @PostConstruct
    public void init() {
        studentDao = new StudentDao();
        id = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("student_id"));
        Student student = studentDao.getById(id);
        studentDto = new StudentDto(student.getName(), student.getAssignedExams());
    }

    public void submit() {
        studentDao.update(id, studentDto);
    }
}
