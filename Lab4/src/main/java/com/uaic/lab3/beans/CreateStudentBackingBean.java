package com.uaic.lab3.beans;

import com.uaic.lab3.daos.StudentDao;
import com.uaic.lab3.dtos.StudentDto;
import lombok.Getter;
import lombok.Setter;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;

@Getter
@Setter
@Named
@SessionScoped
public class CreateStudentBackingBean implements Serializable {
    private final StudentDao studentDao;
    private String name;
    private String assignedExams;

    public CreateStudentBackingBean(){
        studentDao = new StudentDao();
    }

    public void submit() throws SQLException {
        StudentDto student = new StudentDto(name, assignedExams);
        studentDao.create(student);
        clear();
    }

    private void clear(){
        setName(null);
        setAssignedExams(null);
    }
}
