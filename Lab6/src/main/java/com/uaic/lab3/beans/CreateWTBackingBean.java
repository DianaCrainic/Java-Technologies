package com.uaic.lab3.beans;

import com.uaic.lab3.daos.WrittenTestDao;
import com.uaic.lab3.entities.WrittenTest;
import lombok.Getter;
import lombok.Setter;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.sql.Timestamp;

@Getter
@Setter
@Named
@SessionScoped
public class CreateWTBackingBean extends CreateExamBackingBean {
    @EJB
    private WrittenTestDao examDao;

    private String bibliography;

    @Override
    public void submit() {
        WrittenTest writtenTest = new WrittenTest(name, new Timestamp(startingTime.getTime()), duration, bibliography);
        examDao.create(writtenTest);
    }
}
