package com.uaic.lab3.daos;

import com.uaic.lab3.dtos.ResourceDto;
import com.uaic.lab3.entities.Resource;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.List;

@Stateless
public class ResourceDao extends Dao<Resource> {
    public List getAll() {
        return entityManager.createNamedQuery("Resource.getAll").getResultList();
    }

    public Resource getById(Integer id) {
        return entityManager.find(Resource.class, id);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public boolean updateResources(ResourceDto resourceDto) {
        boolean transactionFailed = false;
        try {
            Resource resource = getById(resourceDto.getId());
            int newQuantity = resource.getQuantity() - resourceDto.getQuantity();
            if (newQuantity < 0) {
                throw new IllegalStateException("Resource quantity exceeded");
            }
            resource.setQuantity(newQuantity);
            entityManager.merge(resource);
            entityManager.flush();
        } catch (Throwable throwable) {
            transactionFailed = true;
        }
        return transactionFailed;
    }
}
