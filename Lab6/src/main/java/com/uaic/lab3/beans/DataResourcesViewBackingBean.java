package com.uaic.lab3.beans;

import com.uaic.lab3.daos.ResourceDao;
import com.uaic.lab3.entities.Resource;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Named;
import java.util.List;


/**
 * A stateless session bean that offers methods for
 * checking the availability of a resource.
 */
@Named
@Stateless
@LocalBean
public class DataResourcesViewBackingBean extends DataViewBackingBean<Resource> {
    @EJB
    private ResourceDao resourceDao;

    @PostConstruct
    public void init() {
        entities = resourceDao.getAll();
    }

    public List<Resource> getEntities() {
        entities = resourceDao.getAll();
        return entities;
    }
}
