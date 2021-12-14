package com.uaic.lab7.beans;

import com.uaic.lab7.dtos.AuthenticationDto;
import com.uaic.lab7.services.AuthenticationService;
import lombok.Getter;
import lombok.Setter;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;

@Named
@SessionScoped
public class AuthenticationBean implements Serializable {
    @Getter
    @Setter
    private AuthenticationDto authenticationDto = new AuthenticationDto();

    @Inject
    private AuthenticationService authenticationService;

    public void submit() throws NoSuchAlgorithmException, IOException {
        this.authenticationService.authenticate(authenticationDto);
        FacesContext.getCurrentInstance().getExternalContext().redirect("/Lab7-1.0-SNAPSHOT/index.xhtml");
    }
}
