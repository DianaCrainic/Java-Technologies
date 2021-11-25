package com.uaic.lab3.daos;

import com.uaic.lab3.entities.Resource;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class ResourceDao extends Dao<Resource> {
    public List getAll() {
        return entityManager.createNamedQuery("Resource.getAll").getResultList();
    }
}
