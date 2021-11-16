package com.uaic.lab3.beans;

import com.uaic.lab3.daos.ExamDao;
import com.uaic.lab3.dtos.ExamDto;
import com.uaic.lab3.entities.Exam;
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
public class EditExamBackingBean implements Serializable {
    private Integer id;
    @Getter
    @Setter
    private ExamDto examDto;
    private ExamDao examDao;

    @PostConstruct
    public void init() {
        examDao = new ExamDao();
        id = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("exam_id"));
        Exam exam = examDao.getById(id);
        examDto = new ExamDto(exam.getName(), exam.getStartingTime(), exam.getDuration());
    }

    public void submit() {
        examDao.update(id, examDto);
    }
}
