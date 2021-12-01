package com.uaic.lab7.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor
@Entity
@DiscriminatorValue("author")
public class Author extends User {
    public Author(String username, String password) {
        super(username, password);
    }
}
