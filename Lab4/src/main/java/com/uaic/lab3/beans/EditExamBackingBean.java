package com.uaic.lab3.beans;

import com.uaic.lab3.daos.ExamDao;
import com.uaic.lab3.entities.Exam;
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
public class EditExamBackingBean implements Serializable {
    private final ExamDao examDao;
    private Exam exam;

    public EditExamBackingBean() throws SQLException {
        examDao = new ExamDao();
        Integer id = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext()
                .getRequestParameterMap().get("exam_id"));
        exam = examDao.getById(id);
    }

    public void submit() throws SQLException {
        examDao.update(exam);
    }
}
