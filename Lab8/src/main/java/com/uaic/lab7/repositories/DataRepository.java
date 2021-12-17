package com.uaic.lab7.repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

public abstract class DataRepository implements Serializable {
    @PersistenceContext
    protected EntityManager entityManager;
}
