package com.uaic.Lab9.repositories;

import com.uaic.Lab9.entities.Library;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class LibraryRepository extends DataRepository {

    public Optional<Library> getById(Integer id) {
        return this.entityManager
                .createNamedQuery("Library.getById", Library.class)
                .setParameter("id", id)
                .getResultList()
                .stream()
                .findFirst();
    }
}
