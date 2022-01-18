package com.uaic.Lab9.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
@NamedQueries({
        @NamedQuery(name = "Book.getAll",
                query = "SELECT book FROM Book book"),
        @NamedQuery(name = "Book.getById",
                query = "SELECT book FROM Book book WHERE id = :id")
})
public class Book extends AbstractEntity {
    @Basic(optional = false)
    @Column(name = "title")
    @Schema(required = true, description = "Name of the book")
    private String title;

    @Basic(optional = false)
    @Column(name = "review")
    @Schema(required = true, description = "The review of the book")
    private Integer review;

    @Basic(optional = false)
    @Column(name = "price")
    @Schema(required = true, description = "The price of the book")
    private Integer price;

    @ManyToOne
    @JoinColumn(name = "library_id", nullable = false)
    @Schema(required = true, description = "The library of the book")
    private Library library;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "books_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> authors = new ArrayList<>();

    public Book(String title, Integer review, Integer price, Library library) {
        this.title = title;
        this.review = review;
        this.price = price;
        this.library = library;
    }
}
