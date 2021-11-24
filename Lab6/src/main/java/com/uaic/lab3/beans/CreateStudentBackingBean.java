package com.uaic.lab3.beans;

import com.uaic.lab3.daos.StudentDao;
import com.uaic.lab3.dtos.StudentDto;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Getter
@Setter
@Named
@SessionScoped
public class CreateStudentBackingBean implements Serializable {
    @EJB
    private StudentDao studentDao;
    private String name;
    private String assignedExams;

    public void submit() {
        StudentDto student = new StudentDto(name, assignedExams);
        studentDao.create(student);
        clear();
    }

    private void clear() {
        setName(null);
        setAssignedExams(null);
    }
}
