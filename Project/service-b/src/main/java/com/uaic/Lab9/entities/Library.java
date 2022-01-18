package com.uaic.Lab9.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "libraries")
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name = "Library.getAll",
                query = "SELECT library FROM Library library"),
        @NamedQuery(name = "Library.getByName",
                query = "SELECT library FROM Library library WHERE name = :name"),
        @NamedQuery(name = "Library.getById",
                query = "SELECT library FROM Library library WHERE id = :id")
})
@Schema(name = "Library")
public class Library extends AbstractEntity {
    @Basic(optional = false)
    @Column(name = "name")
    @Schema(required = true, description = "The name of the library")
    private String name;

    @Basic(optional = false)
    @Column(name = "budget")
    @Schema(required = true, description = "The budget of the library")
    private Integer budget;

    public Library(String name, Integer budget) {
        this.name = name;
        this.budget = budget;
    }
}