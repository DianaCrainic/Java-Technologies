package com.uaic.Lab9.services;

import com.uaic.Lab9.entities.Library;
import com.uaic.Lab9.exceptions.EntityNotFoundException;
import com.uaic.Lab9.repositories.LibraryRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Optional;

@ApplicationScoped
public class LibraryService {
    @Inject
    private LibraryRepository libraryRepository;

    public Library getById(Integer id) {
        Optional<Library> library = this.libraryRepository.getById(id);
        if (library.isPresent()) {
            return library.get();
        }
        throw new EntityNotFoundException("Library", id);
    }
}
