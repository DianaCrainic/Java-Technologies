package com.uaic.Lab9.services;

import com.uaic.Lab9.entities.Group;
import com.uaic.Lab9.exceptions.EntityNotFoundException;
import com.uaic.Lab9.repositories.GroupRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Optional;

@ApplicationScoped
public class GroupService implements Serializable {
    @Inject
    private GroupRepository groupRepository;

    public Group getByName(String name) {
        Optional<Group> group = this.groupRepository.getByName(name);
        if (!group.isPresent()) {
            throw new EntityNotFoundException("Group");
        }
        return group.get();
    }
}