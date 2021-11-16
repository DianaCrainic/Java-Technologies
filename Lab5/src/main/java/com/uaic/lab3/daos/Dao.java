package com.uaic.lab3.daos;

import com.uaic.lab3.database.Database;
import com.uaic.lab3.entities.AbstractEntity;
import javax.persistence.EntityManager;

public abstract class Dao<T extends AbstractEntity> {
    protected EntityManager entityManager;

    public Dao() {
        entityManager = Database.getInstance().getEntityManager();
    }

    public void create(T entity) {
        beginTransaction();
        entityManager.persist(entity);
        entityManager.flush();
        commitTransaction();
    }

    protected void beginTransaction() {
        try {
            entityManager.getTransaction().begin();
        } catch (IllegalStateException e) {
            rollbackTransaction();
        }
    }

    protected void commitTransaction() {
        try {
            entityManager.getTransaction().commit();
        } catch (IllegalStateException e) {
            rollbackTransaction();
        }
    }

    protected void rollbackTransaction() {
        try {
            entityManager.getTransaction().rollback();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }
}
