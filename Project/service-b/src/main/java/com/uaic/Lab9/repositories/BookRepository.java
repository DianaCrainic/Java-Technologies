package com.uaic.Lab9.repositories;


import com.uaic.Lab9.entities.Book;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class BookRepository extends DataRepository {
    public List<Book> getAll() {
        return this.entityManager
                .createNamedQuery("Book.getAll", Book.class)
                .getResultList();
    }

    public Optional<Book> getById(Integer id) {
        return this.entityManager
                .createNamedQuery("Book.getById", Book.class)
                .setParameter("id", id)
                .getResultList()
                .stream()
                .findFirst();
    }

    public void create(Book book) {
        this.entityManager.persist(book);
    }

    public void update(Book book) {
        this.entityManager.merge(book);
    }

    public void remove(Book book) {
        this.entityManager.remove(entityManager.contains(book) ? book : entityManager.merge(book));
    }
}
