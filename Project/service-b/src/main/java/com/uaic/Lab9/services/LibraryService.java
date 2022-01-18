package com.uaic.Lab9.services;

import com.uaic.Lab9.dtos.LibraryDto;
import com.uaic.Lab9.entities.Library;
import com.uaic.Lab9.exceptions.EntityNotFoundException;
import com.uaic.Lab9.repositories.LibraryRepository;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class LibraryService {
    @Inject
    private LibraryRepository libraryRepository;

    private List<Library> libraries;

    @Inject
    @Any
    private Event<Library> libraryEvent;

    @PostConstruct
    public void init() {
        this.libraries = this.libraryRepository.getAll();
    }

    public void onLibraryUpload(@Observes Library library) {
        this.libraries = this.libraryRepository.getAll();
    }

    public List<Library> getAll() {
        return libraries;
    }

    public Library getById(Integer id) {
        Optional<Library> library = this.libraryRepository.getById(id);
        if (library.isPresent()) {
            return library.get();
        }
        throw new EntityNotFoundException("Library", id);
    }

    public Library create(LibraryDto libraryDto) {
        Library library = new Library(
                libraryDto.getName(),
                libraryDto.getBudget()
        );
        this.libraryRepository.create(library);
        libraryEvent.fire(library);
        return library;
    }

    public void update(Integer id, LibraryDto libraryDto) {
        Library library = getById(id);
        library.setName(libraryDto.getName());
        library.setBudget(libraryDto.getBudget());
        this.libraryRepository.update(library);
        libraryEvent.fire(library);
    }

    public void remove(Integer id) {
        Library library = getById(id);
        this.libraryRepository.remove(library);
        libraryEvent.fire(library);
    }
}
