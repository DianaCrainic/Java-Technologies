package com.uaic.lab7.repositories;

import com.uaic.lab7.entities.TimeFrame;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class TimeFrameRepository extends DataRepository {
    public List<TimeFrame> getAll() {
        return entityManager.createNamedQuery("TimeFrame.getAll").getResultList();
    }
}
