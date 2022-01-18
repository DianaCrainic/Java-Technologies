package com.uaic.Lab9.repositories;


import com.uaic.Lab9.entities.Author;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class AuthorRepository extends DataRepository {
    public List<Author> getAll() {
        return this.entityManager
                .createNamedQuery("Author.getAll", Author.class)
                .getResultList();
    }

    public Optional<Author> getById(Integer id) {
        return this.entityManager
                .createNamedQuery("Author.getById", Author.class)
                .setParameter("id", id)
                .getResultList()
                .stream()
                .findFirst();
    }

    public void create(Author author) {
        this.entityManager.persist(author);
    }

    public void update(Author author) {
        this.entityManager.merge(author);
    }

    public void remove(Author author) {
        this.entityManager.remove(entityManager.contains(author) ? author : entityManager.merge(author));
    }
}
