package com.uaic.lab7.repositories;

import com.uaic.lab7.entities.TimeFrame;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class TimeFrameRepository extends DataRepository {
    public List<TimeFrame> getAll() {
        return entityManager.createNamedQuery("TimeFrame.getAll").getResultList();
    }
}
