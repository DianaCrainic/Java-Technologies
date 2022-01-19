package com.uaic.Lab9.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.*;

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
    @JoinColumn(name = "library_id")
    @Schema(required = true, description = "The library of the book")
    private Library library;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    @Schema(required = true, description = "The author of the book")
    private Author author;

    public Book(String title, Integer review, Integer price, Author author) {
        this.title = title;
        this.review = review;
        this.price = price;
        this.author = author;
    }
}
