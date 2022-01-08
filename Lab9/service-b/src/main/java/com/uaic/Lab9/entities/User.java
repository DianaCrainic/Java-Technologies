package com.uaic.Lab9.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name = "User.getByUsername",
                query = "SELECT user FROM User user WHERE username = :username"),
        @NamedQuery(name = "User.getByUsernameAndPassword",
                query = "SELECT user FROM User user WHERE username = :username AND password = :password")
})
@Schema(name = "User")
public class User implements Serializable {
    @Id
    @Basic(optional = false)
    @Column(name = "username", unique = true)
    @Schema(required = true, description = "Username of the user")
    private String username;

    @Basic(optional = false)
    @Column(name = "password")
    @Schema(required = true, description = "Password of the user")
    @JsonbTransient
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "user_groups",
            joinColumns = @JoinColumn(name = "username"),
            inverseJoinColumns = @JoinColumn(name = "group_name"))
    private List<Group> groups = new ArrayList<>();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void addGroup(Group group) {
        this.groups.add(group);
    }
}