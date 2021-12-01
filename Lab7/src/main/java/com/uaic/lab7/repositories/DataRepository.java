package com.uaic.lab7.repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class DataRepository {
    @PersistenceContext
    protected EntityManager entityManager;
}
