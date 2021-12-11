package com.uaic.lab7.repositories;

import com.uaic.lab7.entities.Document;

import javax.ejb.Stateless;
import javax.transaction.Transactional;
import java.util.List;

@Stateless
public class DocumentRepository extends DataRepository {
    public List<Document> getAll() {
        return entityManager.createNamedQuery("Document.getAll").getResultList();
    }

    public Document getByRegistrationNumber(Integer registrationNumber) {
        return (Document) this.entityManager.createNamedQuery("Document.getByRegistrationNumber")
                .setParameter("registrationNumber", registrationNumber)
                .getSingleResult();
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void create(Document document) {
        entityManager.persist(document);
    }
}
