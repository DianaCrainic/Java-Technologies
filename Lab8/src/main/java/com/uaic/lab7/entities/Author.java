package com.uaic.lab7.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@DiscriminatorValue("author")
public class Author extends User {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "author")
    private List<Document> documents = new ArrayList<>();

    public Author(String username, String password) {
        super(username, password);
    }
}
