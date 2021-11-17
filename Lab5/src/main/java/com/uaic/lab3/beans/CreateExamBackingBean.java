package com.uaic.lab3.beans;

import com.uaic.lab3.daos.ExamDao;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public abstract class CreateExamBackingBean implements Serializable {
    protected ExamDao examDao;
    protected String name;
    protected Date startingTime;
    protected Integer duration;

    public abstract void submit();
}