package com.uaic.Lab9.repositories;


import com.uaic.Lab9.entities.Document;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class DocumentRepository extends DataRepository {
    public List<Document> getAll() {
        return this.entityManager
                .createNamedQuery("Document.getAll", Document.class)
                .getResultList();
    }

    public Optional<Document> getById(Integer id) {
        return this.entityManager
                .createNamedQuery("Document.getById", Document.class)
                .setParameter("id", id)
                .getResultList()
                .stream()
                .findFirst();
    }

    public void create(Document document) {
        this.entityManager.persist(document);
    }

    public void update(Document document) {
        this.entityManager.merge(document);
    }

    public void remove(Document document) {
        this.entityManager.remove(entityManager.contains(document) ? document : entityManager.merge(document));
    }
}
