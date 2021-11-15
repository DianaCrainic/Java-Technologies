package com.uaic.lab3.beans;

import com.uaic.lab3.daos.ExamDao;
import com.uaic.lab3.entities.Exam;
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
public class EditExamBackingBean implements Serializable {
    private final ExamDao examDao;
    private Exam exam;

    public EditExamBackingBean() throws NamingException, SQLException {
        examDao = new ExamDao();
        Integer id = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext()
                .getRequestParameterMap().get("exam_id"));
        exam = examDao.getById(id);
    }

    public void submit() throws SQLException {
        examDao.update(exam);
    }
}
