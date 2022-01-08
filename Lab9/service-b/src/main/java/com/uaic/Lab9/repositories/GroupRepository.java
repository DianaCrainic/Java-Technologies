package com.uaic.Lab9.repositories;

import com.uaic.Lab9.entities.Group;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class GroupRepository extends DataRepository {
    public Optional<Group> getByName(String name) {
        return this.entityManager
                .createNamedQuery("Group.getByName", Group.class)
                .setParameter("name", name)
                .getResultList()
                .stream()
                .findFirst();
    }
}