package com.uaic.Lab9.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "documents")
@NamedQueries({
        @NamedQuery(name = "Document.getAll",
                query = "SELECT document FROM Document document"),
        @NamedQuery(name = "Document.getById",
                query = "SELECT document FROM Document document WHERE id = :id")
})
public class Document extends AbstractEntity {
    @Basic(optional = false)
    @Column(name = "name")
    @Schema(required = true, description = "Name of the document")
    private String name;

    @Basic(optional = false)
    @Column(name = "registration_number", unique = true)
    @Schema(required = true, description = "Registration number of the document")
    private Integer registrationNumber;

    @Basic(optional = false)
    @Column(name = "content")
    @Schema(required = true, description = "Content of the document")
    private byte[] content;

    @ManyToOne
    @JoinColumn(name = "author_username", referencedColumnName = "username", nullable = false)
    @Schema(required = true, description = "The author of the document")
    private User author;

    public Document(String name, Integer registrationNumber, byte[] content) {
        this.name = name;
        this.registrationNumber = registrationNumber;
        this.content = content;
    }
}
