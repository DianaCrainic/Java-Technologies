package com.uaic.lab7.services;

import com.uaic.lab7.dtos.AuthenticationDto;
import com.uaic.lab7.entities.User;
import com.uaic.lab7.repositories.UserRepository;
import lombok.Getter;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;

@Named
@SessionScoped
public class AuthenticationService implements Serializable {
    @Inject
    private UserRepository userRepository;

    @Getter
    private User user;

    public void authenticate(AuthenticationDto authenticationDto) throws NoSuchAlgorithmException {
        this.user = this.userRepository.getByUsernameAndPassword(
                authenticationDto.getUsername(),
                authenticationDto.getPassword()
        );
    }

    public void logout() throws IOException {
        this.user = null;
        FacesContext.getCurrentInstance().getExternalContext().redirect("/Lab7-1.0-SNAPSHOT/authentication.xhtml");
    }
}
