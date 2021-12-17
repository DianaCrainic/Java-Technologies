package com.uaic.lab7.services;

import com.uaic.lab7.dtos.RegistrationDto;
import com.uaic.lab7.entities.Author;
import com.uaic.lab7.entities.User;
import com.uaic.lab7.exceptions.EntityNotFoundException;
import com.uaic.lab7.repositories.UserRepository;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Named
@SessionScoped
public class UserService implements Serializable {
    @Inject
    private UserRepository userRepository;

    public Author getAuthorById(Integer id) {
        Optional<Author> author = userRepository.getAuthorById(id);
        if (!author.isPresent()) {
            throw new EntityNotFoundException("Author", id);
        }
        return author.get();
    }

    public void register(RegistrationDto registerUserDto) throws NoSuchAlgorithmException {
        User user = new User(registerUserDto.getUsername(), registerUserDto.getPassword());
        userRepository.create(user, registerUserDto.getRole());
    }
}
