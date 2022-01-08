package com.uaic.Lab9.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDto {
    @NotNull
    @Size(min = 4)
    private String username;

    @NotNull
    @Size(min = 4)
    private String password;

    @NotNull
    private String group;
}
