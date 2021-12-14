package com.uaic.lab7.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor
@Entity
@DiscriminatorValue("admin")
public class Admin extends User {
    public Admin(String username, String password) {
        super(username, password);
    }
}
