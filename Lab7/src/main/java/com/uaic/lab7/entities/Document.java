package com.uaic.lab7.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "documents")
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name = "Document.getAll",
                query = "SELECT document FROM Document document")
})
public class Document extends AbstractEntity {
    @Basic(optional = false)
    @Column(name = "name")
    private String name;

    @Basic(optional = false)
    @Column(name = "registration_number", unique = true)
    private Integer registrationNumber;

    @Basic(optional = false)
    @Column(name = "content")
    private byte[] content;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id", nullable = false)
    private Author author;

    public Document(String name, Integer registrationNumber, byte[] content) {
        this.name = name;
        this.registrationNumber = registrationNumber;
        this.content = content;
    }
}