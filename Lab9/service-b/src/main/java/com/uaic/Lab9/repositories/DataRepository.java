package com.uaic.Lab9.repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class DataRepository {
    @PersistenceContext
    protected EntityManager entityManager;
}
