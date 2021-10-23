package com.uaic.lab3.beans;

import com.uaic.lab3.daos.ExamDao;
import com.uaic.lab3.dtos.ExamDto;
import lombok.Getter;
import lombok.Setter;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
@Named
@SessionScoped
public class CreateExamBackingBean implements Serializable {
    private final ExamDao examDao;
    private String name;
    private Date startingTime;
    private Integer duration;

    public CreateExamBackingBean() {
        examDao = new ExamDao();
    }

    public void submit() throws SQLException {
        ExamDto exam = new ExamDto(name, new Timestamp(startingTime.getTime()), duration);
        examDao.create(exam);
        clear();
    }

    private void clear(){
        setName(null);
        setStartingTime(null);
        setDuration(null);
    }
}