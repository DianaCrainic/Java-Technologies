package com.uaic.lab7.repositories;

import com.uaic.lab7.entities.Document;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;

//@Stateless
@ApplicationScoped
public class DocumentRepository extends DataRepository {
    public List<Document> getAll() {
        return entityManager.createNamedQuery("Document.getAll").getResultList();
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void create(Document document) {
        entityManager.persist(document);
    }
}
