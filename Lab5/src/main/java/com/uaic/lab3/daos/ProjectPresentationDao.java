package com.uaic.lab3.daos;

import com.uaic.lab3.entities.ProjectPresentation;

import java.util.List;

public class ProjectPresentationDao extends ExamDao<ProjectPresentation> {
    @Override
    public List<ProjectPresentation> getAll() {
        return entityManager.createNamedQuery("ProjectPresentation.getAll").getResultList();
    }
}
