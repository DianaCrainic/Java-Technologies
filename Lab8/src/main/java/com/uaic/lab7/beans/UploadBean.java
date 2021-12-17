package com.uaic.lab7.beans;

import com.uaic.lab7.dtos.CreateDocumentDto;
import com.uaic.lab7.entities.Document;
import com.uaic.lab7.interceptors.ValidTimeFrame;
import com.uaic.lab7.services.AuthenticationService;
import com.uaic.lab7.services.DocumentService;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.file.UploadedFile;

import javax.enterprise.event.Event;
import javax.enterprise.inject.Any;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class UploadBean implements Serializable {
    @Inject
    private DocumentService documentService;

    @Inject
    private AuthenticationService authenticationService;

    @Getter
    @Setter
    private UploadedFile file;

    @Inject
    @Any
    private Event<Document> documentEvent;

    @ValidTimeFrame
    public void upload() {
        if (file != null) {
                Integer authorId = this.authenticationService.getUser().getId();
                CreateDocumentDto createDocumentDto =
                        new CreateDocumentDto(file.getFileName(), file.getContent(), authorId);
                Document document = documentService.create(createDocumentDto);
                documentEvent.fire(document);
                FacesMessage message = new FacesMessage("Success!", file.getFileName() + " was uploaded.");
                FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
}

