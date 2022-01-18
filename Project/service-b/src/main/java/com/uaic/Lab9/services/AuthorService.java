package com.uaic.Lab9.services;

import com.uaic.Lab9.dtos.AuthorDto;
import com.uaic.Lab9.entities.Author;
import com.uaic.Lab9.exceptions.EntityNotFoundException;
import com.uaic.Lab9.repositories.AuthorRepository;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class AuthorService implements Serializable {
    @Inject
    private AuthorRepository authorRepository;

    private List<Author> authors;

    @Inject
    @Any
    private Event<Author> authorEvent;

    @PostConstruct
    public void init() {
        this.authors = this.authorRepository.getAll();
    }

    public void onAuthorUpload(@Observes Author author) {
        this.authors = this.authorRepository.getAll();
    }

    public List<Author> getAll() {
        return authors;
    }

    public Author getById(Integer id) {
        Optional<Author> author = this.authorRepository.getById(id);
        if (author.isPresent()) {
            return author.get();
        }
        throw new EntityNotFoundException("Author", id);
    }

    public Author create(AuthorDto authorDto) {
        Author author = new Author(
                authorDto.getName()
        );
        this.authorRepository.create(author);
        authorEvent.fire(author);
        return author;
    }

    public void update(Integer id, AuthorDto authorDto) {
        Author author = getById(id);
        author.setName(authorDto.getName());
        this.authorRepository.update(author);
        authorEvent.fire(author);
    }

    public void remove(Integer id) {
        Author author = getById(id);
        this.authorRepository.remove(author);
        authorEvent.fire(author);
    }
}