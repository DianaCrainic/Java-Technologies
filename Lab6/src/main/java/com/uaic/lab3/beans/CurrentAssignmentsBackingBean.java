package com.uaic.lab3.beans;

import com.uaic.lab3.daos.ExamDao;
import com.uaic.lab3.daos.ResourceDao;
import lombok.Getter;
import lombok.Setter;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.inject.Named;
import java.util.HashMap;
import java.util.Map;

import static javax.ejb.ConcurrencyManagementType.BEAN;

/**
 * A singleton session bean that keeps an in-memory map of the current assignments.
 * The map will be instantiated at application startup and updated whenever the assignments change.
 */
@Named
@Singleton
@ConcurrencyManagement(BEAN)
public class CurrentAssignmentsBackingBean {
    @Getter
    @Setter
    private Map<Integer, Map<Integer, Integer>> resourcesAssignments = new HashMap<>();

    @EJB
    private ResourceDao resourceDao;

    @EJB
    private ExamDao examDao;

    public String getExamNameById(Integer examId) {
        return examDao.getById(examId).getName();
    }

    public String getResourceNameById(Integer resourceId) {
        return resourceDao.getById(resourceId).getName();
    }

    public void addResourceToExam(Integer examId, Integer resourceId, Integer quantity) {
        resourcesAssignments.computeIfAbsent(examId, k -> new HashMap<>());
        Map<Integer, Integer> assignedResourcesForExam = resourcesAssignments.get(examId);
        if (assignedResourcesForExam.containsKey(resourceId)) {
            assignedResourcesForExam.put(resourceId, assignedResourcesForExam.get(resourceId) + quantity);
        } else {
            assignedResourcesForExam.put(resourceId, quantity);
        }
    }
}
