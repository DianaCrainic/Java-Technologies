package com.uaic.lab3.Beans;

import com.uaic.lab3.entities.Exam;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;

@ManagedBean(name = "backingExams")
@SessionScoped
public class BackingExams {

    public List<Exam> findAllExams(){
        return null;
    }
}
