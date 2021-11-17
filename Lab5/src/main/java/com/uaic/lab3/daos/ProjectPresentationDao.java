package com.uaic.lab3.daos;

import com.uaic.lab3.entities.ProjectPresentation;
import com.uaic.lab3.filters.DateFilters;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class ProjectPresentationDao extends ExamDao<ProjectPresentation> {
    @Override
    public List<ProjectPresentation> getAll(DateFilters dateFilters) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ProjectPresentation> query = builder.createQuery(ProjectPresentation.class);
        Root<ProjectPresentation> root = query.from(ProjectPresentation.class);
        Predicate filter = builder.conjunction();

        if (dateFilters.isUseStartDateFilter() && dateFilters.getStartDate() != null) {
            filter = builder.and(filter, builder.greaterThanOrEqualTo(root.get("startingTime"), dateFilters.getStartDate()));
        }

        if (dateFilters.isUseEndDateFilter() && dateFilters.getEndDate() != null) {
            filter = builder.and(filter, builder.lessThanOrEqualTo(root.get("startingTime"), dateFilters.getEndDate()));
        }
        query.where(filter);
        return entityManager.createQuery(query).getResultList();
    }
}