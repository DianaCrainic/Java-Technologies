package com.uaic.lab7.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@DiscriminatorValue("author")
@NamedQueries({
        @NamedQuery(name = "Author.getById",
                query = "SELECT author FROM Author author WHERE id = :id")
})
public class Author extends User {
    public Author(String username, String password) {
        super(username, password);
    }
}
