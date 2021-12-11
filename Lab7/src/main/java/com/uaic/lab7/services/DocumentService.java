package com.uaic.lab7.services;

import com.uaic.lab7.dtos.CreateDocumentDto;
import com.uaic.lab7.entities.Author;
import com.uaic.lab7.entities.Document;
import com.uaic.lab7.repositories.DocumentRepository;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class DocumentService implements Serializable {
    @EJB
    private DocumentRepository documentRepository;

    @Inject
    private AuthenticationService authenticationService;

    public List<Document> getAll() {
        return this.documentRepository.getAll();
    }

    public Document getByRegistrationNumber(Integer registrationNumber) {
        return this.documentRepository.getByRegistrationNumber(registrationNumber);
    }

    public Document create(CreateDocumentDto createDocumentDto) {
        Document document = new Document(
                createDocumentDto.getName(),
                createDocumentDto.getRegistrationNumber(),
                createDocumentDto.getContent()
        );
        document.setAuthor((Author) this.authenticationService.getUser());

        this.documentRepository.create(document);
        return document;
    }
}
