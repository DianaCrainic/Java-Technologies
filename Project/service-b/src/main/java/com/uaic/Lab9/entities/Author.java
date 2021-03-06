package com.uaic.Lab9.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "authors")
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name = "Author.getAll",
                query = "SELECT author FROM Author author"),
        @NamedQuery(name = "Author.getByName",
                query = "SELECT author FROM Author author WHERE name = :name"),
        @NamedQuery(name = "Author.getById",
                query = "SELECT author FROM Author author WHERE id = :id")
})
@Schema(name = "Author")
public class Author extends AbstractEntity {
    @Basic(optional = false)
    @Column(name = "name")
    @Schema(required = true, description = "The name of the author")
    private String name;

    public Author(String name) {
        this.name = name;
    }

}