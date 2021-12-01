package com.uaic.lab7.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "users")
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role",
        discriminatorType = DiscriminatorType.STRING)
@NamedQueries({
        @NamedQuery(name = "User.getByUsername",
                query = "SELECT user FROM User user WHERE username = :username"),
        @NamedQuery(name = "User.getByUsernameAndPassword",
                query = "SELECT user FROM User user WHERE username = :username AND password = :password")
})
public class User extends AbstractEntity {
    @Basic(optional = false)
    @Column(name = "username", unique = true)
    private String username;

    @Basic(optional = false)
    @Column(name = "password")
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}