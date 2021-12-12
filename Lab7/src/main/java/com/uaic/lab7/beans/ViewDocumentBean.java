package com.uaic.lab7.beans;

import com.uaic.lab7.entities.Document;
import com.uaic.lab7.services.DocumentService;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ApplicationScoped
public class ViewDocumentBean implements Serializable {
    @Inject
    private DocumentService documentService;

    @Getter
    @Setter
    private List<Document> documents;

    @PostConstruct
    public void init() {
        documents = documentService.getAll();
    }

    public void onDocumentUpload(@Observes Document document) {
        documents.add(document);
    }
}