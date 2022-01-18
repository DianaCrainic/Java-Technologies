package com.uaic.Lab9.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "books_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Book> books = new ArrayList<>();

    public Author(String name) {
        this.name = name;
    }

}