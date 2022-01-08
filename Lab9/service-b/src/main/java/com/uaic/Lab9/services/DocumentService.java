package com.uaic.Lab9.services;

import com.uaic.Lab9.dtos.CreateDocumentDto;
import com.uaic.Lab9.dtos.UpdateDocumentDto;
import com.uaic.Lab9.entities.Document;
import com.uaic.Lab9.entities.User;
import com.uaic.Lab9.exceptions.EntityNotFoundException;
import com.uaic.Lab9.producers.DocumentRegistrationNumber;
import com.uaic.Lab9.repositories.DocumentRepository;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class DocumentService implements Serializable {
    @Inject
    private DocumentRepository documentRepository;

    @Inject
    private UserService userService;

    private List<Document> documents;
    private Set<String> authorUsernames;

    @Inject
    @DocumentRegistrationNumber
    private Instance<Integer> registrationNumber;

    @Inject
    @Any
    private Event<Document> documentEvent;

    @PostConstruct
    public void init() {
        this.documents = this.documentRepository.getAll();
        this.authorUsernames = new HashSet<>();
    }

    public void onDocumentUpload(@Observes Document document) {
        this.documents = this.documentRepository.getAll();
    }

    public List<Document> getAll(String authorUsername) {
        if (authorUsername == null) {
            return documents;
        }
        if (!authorUsernames.contains(authorUsername)) {
            userService.getByUsernameAndGroup(authorUsername, "author");
            authorUsernames.add(authorUsername);
        }
        return filterDocumentByAuthorUsername(authorUsername);
    }

    public Document getById(Integer id) {
        Optional<Document> document = this.documentRepository.getById(id);
        if (document.isPresent()) {
            return document.get();
        }
        throw new EntityNotFoundException("Document", id);
    }

    public Document create(CreateDocumentDto createDocumentDto) {
        String authorUsername = createDocumentDto.getAuthorUsername();
        Document document = new Document(
                createDocumentDto.getName(),
                registrationNumber.get(),
                createDocumentDto.getContent().getBytes()
        );
        User user = userService.getByUsernameAndGroup(authorUsername, "author");
        document.setAuthor(user);
        this.documentRepository.create(document);
        documentEvent.fire(document);
        return document;
    }

    public void update(Integer id, UpdateDocumentDto updateDocumentDto) {
        Document document = getById(id);
        document.setName(updateDocumentDto.getName());
        document.setContent(updateDocumentDto.getContent().getBytes());
        this.documentRepository.update(document);
        documentEvent.fire(document);
    }

    public void remove(Integer id) {
        Document document = getById(id);
        this.documentRepository.remove(document);
        documentEvent.fire(document);
    }

    private List<Document> filterDocumentByAuthorUsername(String authorUsername) {
        return this.documents.stream()
                .filter(document -> document.getAuthor().getUsername().equals(authorUsername))
                .collect(Collectors.toList());
    }
}