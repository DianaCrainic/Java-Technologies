package com.uaic.lab7.repositories;

import com.uaic.lab7.entities.Document;

import javax.ejb.Stateless;
import java.util.List;
import java.util.Optional;

@Stateless
public class DocumentRepository extends DataRepository {
    public List<Document> getAll() {
        return this.entityManager
                .createNamedQuery("Document.getAll", Document.class)
                .getResultList();
    }

    public void create(Document document) {
        this.entityManager.persist(document);
    }

    public Optional<Document> getById(Integer id) {
        return this.entityManager
                .createNamedQuery("Document.getById", Document.class)
                .setParameter("id", id)
                .getResultList()
                .stream()
                .findFirst();
    }

    public void update(Document document) {
        this.entityManager.merge(document);
    }

    public void remove(Document document) {
        this.entityManager.remove(entityManager.contains(document) ? document : entityManager.merge(document));
    }
}
