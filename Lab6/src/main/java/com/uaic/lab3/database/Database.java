package com.uaic.lab3.database;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class Database {
    private static Database instance;
    private final EntityManager entityManager;

    private Database() {
        entityManager = Persistence.createEntityManagerFactory("SchedulerPU").createEntityManager();
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}