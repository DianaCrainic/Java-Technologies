package com.uaic.lab7.services;

import com.uaic.lab7.dtos.CreateDocumentDto;
import com.uaic.lab7.dtos.UpdateDocumentDto;
import com.uaic.lab7.entities.Author;
import com.uaic.lab7.entities.Document;
import com.uaic.lab7.exceptions.EntityNotFoundException;
import com.uaic.lab7.interceptors.SubmissionLog;
import com.uaic.lab7.producers.DocumentRegistrationNumber;
import com.uaic.lab7.repositories.DocumentRepository;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Named
@SessionScoped
public class DocumentService implements Serializable {
    @Inject
    private DocumentRepository documentRepository;

    @Inject
    private UserService userService;

    private List<Document> documents;
    private Set<Integer> authorIds;

    @Inject
    @DocumentRegistrationNumber
    private Instance<Integer> registrationNumber;

    @Inject
    @Any
    private Event<Document> documentEvent;

    @PostConstruct
    public void init() {
        this.documents = this.documentRepository.getAll();
        this.authorIds = new HashSet<>();
    }

    public void onDocumentUpload(@Observes Document document) {
        this.documents = this.documentRepository.getAll();
    }

    public List<Document> getAll(Integer authorId) {
        if (authorId == null) {
            return documents;
        }

        if (!authorIds.contains(authorId)) {
            userService.getAuthorById(authorId);
            authorIds.add(authorId);
        }
        return this.documents.stream()
                .filter(document -> document.getAuthor().getId().equals(authorId))
                .collect(Collectors.toList());
    }

    public Document getById(Integer id) {
        Optional<Document> document = this.documentRepository.getById(id);
        if (document.isPresent()) {
            return document.get();
        }
        throw new EntityNotFoundException("Document", id);
    }

    @SubmissionLog
    public Document create(CreateDocumentDto createDocumentDto) {
        Document document = new Document(
                createDocumentDto.getName(),
                registrationNumber.get(),
                createDocumentDto.getContent()
        );
        int authorId = createDocumentDto.getAuthorId();
        Author author = userService.getAuthorById(authorId);
        document.setAuthor(author);
        this.documentRepository.create(document);
        documentEvent.fire(document);
        return document;
    }

    public void update(Integer id, UpdateDocumentDto updateDocumentDto) {
        Document document = getById(id);
        document.setName(updateDocumentDto.getName());
        document.setContent(updateDocumentDto.getContent());
        this.documentRepository.update(document);
        documentEvent.fire(document);
    }

    public void remove(Integer id) {
        Document document = getById(id);
        this.documentRepository.remove(document);
        documentEvent.fire(document);
    }
}
