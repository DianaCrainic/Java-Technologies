package com.uaic.lab7.beans;

import com.uaic.lab7.dtos.RegistrationDto;
import com.uaic.lab7.interceptors.ValidTimeFrame;
import com.uaic.lab7.services.RegistrationService;
import lombok.Getter;
import lombok.Setter;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;

@Named
@SessionScoped
public class RegistrationBean implements Serializable {
    @Getter
    @Setter
    private RegistrationDto registrationDto = new RegistrationDto();

    @Inject
    private RegistrationService registrationService;

    @ValidTimeFrame
    public void submit() throws NoSuchAlgorithmException {
        this.registrationService.register(registrationDto);
    }
}