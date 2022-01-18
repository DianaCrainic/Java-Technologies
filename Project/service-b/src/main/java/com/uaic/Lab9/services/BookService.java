package com.uaic.Lab9.services;

import com.uaic.Lab9.dtos.CreateBookDto;
import com.uaic.Lab9.dtos.UpdateBookDto;
import com.uaic.Lab9.entities.Book;
import com.uaic.Lab9.entities.Library;
import com.uaic.Lab9.exceptions.EntityNotFoundException;
import com.uaic.Lab9.repositories.BookRepository;

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
public class BookService implements Serializable {
    @Inject
    private BookRepository bookRepository;

    @Inject
    private LibraryService libraryService;

    private List<Book> books;

    @Inject
    @Any
    private Event<Book> bookEvent;

    @PostConstruct
    public void init() {
        this.books = this.bookRepository.getAll();
    }

    public void onBookUpload(@Observes Book book) {
        this.books = this.bookRepository.getAll();
    }

    public List<Book> getAll() {
        return books;
    }

    public Book getById(Integer id) {
        Optional<Book> book = this.bookRepository.getById(id);
        if (book.isPresent()) {
            return book.get();
        }
        throw new EntityNotFoundException("Book", id);
    }

    public Book create(CreateBookDto createBookDto) {
        Library library = libraryService.getById(createBookDto.getLibraryId());
        Book book = new Book(
                createBookDto.getTitle(),
                createBookDto.getReview(),
                createBookDto.getPrice(),
                library
        );
        this.bookRepository.create(book);
        bookEvent.fire(book);
        return book;
    }

    public void update(Integer id, UpdateBookDto updateBookDto) {
        Book book = getById(id);
        book.setTitle(updateBookDto.getTitle());
        book.setReview(updateBookDto.getReview());
        book.setPrice(updateBookDto.getPrice());
        this.bookRepository.update(book);
        bookEvent.fire(book);
    }

    public void remove(Integer id) {
        Book book = getById(id);
        this.bookRepository.remove(book);
        bookEvent.fire(book);
    }
}