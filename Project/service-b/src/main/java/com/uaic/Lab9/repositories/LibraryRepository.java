package com.uaic.Lab9.repositories;

import com.uaic.Lab9.entities.Library;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class LibraryRepository extends DataRepository {

    public List<Library> getAll() {
        return this.entityManager
                .createNamedQuery("Library.getAll", Library.class)
                .getResultList();
    }

    public Optional<Library> getById(Integer id) {
        return this.entityManager
                .createNamedQuery("Library.getById", Library.class)
                .setParameter("id", id)
                .getResultList()
                .stream()
                .findFirst();
    }

    public void create(Library library) {
        this.entityManager.persist(library);
    }

    public void update(Library library) {
        this.entityManager.merge(library);
    }

    public void remove(Library library) {
        this.entityManager.remove(entityManager.contains(library) ? library : entityManager.merge(library));
    }
}
