package com.uaic.lab7.services;

import com.uaic.lab7.dtos.RegistrationDto;
import com.uaic.lab7.entities.User;
import com.uaic.lab7.repositories.UserRepository;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;

@Named
@SessionScoped
public class RegistrationService implements Serializable {
    @EJB
    private UserRepository userRepository;

    public void register(RegistrationDto registrationDto) throws NoSuchAlgorithmException {
        User user = new User(registrationDto.getUsername(), registrationDto.getPassword());
        userRepository.create(user, registrationDto.getRole());
    }
}
