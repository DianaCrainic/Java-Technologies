package com.uaic.lab3.beans;

import com.uaic.lab3.daos.ExamDao;
import com.uaic.lab3.daos.ResourceDao;
import com.uaic.lab3.dtos.ResourceDto;
import com.uaic.lab3.entities.Exam;
import lombok.Getter;
import lombok.Setter;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * A stateful session bean responsible with the assignment of one or more resource to a specific exam.
 * The assignment should be atomic, either all resources are successfully assigned,
 * or the transaction will be rolled back.
 */
@Named
@Getter
@Setter
@Stateful
@LocalBean
@SessionScoped
public class ResourceAssignmentBackingBean implements Serializable {
    @EJB
    private ResourceDao resourceDao;

    @EJB
    private ExamDao examDao;

    private int examId;
    private int resourceId;
    private int quantity;

    @EJB
    private CurrentAssignmentsBackingBean currentAssignmentsBackingBean;

    public void submit() {
        Exam exam = examDao.getById(this.examId);
        if (exam != null) {
            Integer resourceId = this.resourceId;
            Integer quantity = this.quantity;
            ResourceDto resourceDto = new ResourceDto(resourceId, quantity);
            boolean isUpdateFailed = resourceDao.updateResources(resourceDto);
            if (!isUpdateFailed) {
                currentAssignmentsBackingBean.addResourceToExam(examId, this.resourceId, this.quantity);
            }
        }
    }
}


