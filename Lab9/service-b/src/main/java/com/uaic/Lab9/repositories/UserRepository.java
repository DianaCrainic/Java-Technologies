package com.uaic.Lab9.repositories;

import com.uaic.Lab9.entities.User;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class UserRepository extends DataRepository {
    public Optional<User> getByUsername(String username) {
        return this.entityManager
                .createNamedQuery("User.getByUsername", User.class)
                .setParameter("username", username)
                .getResultList()
                .stream()
                .findFirst();
    }


    public boolean existsByUsername(String username) {
        return this.entityManager
                .createNamedQuery("User.getByUsername")
                .setParameter("username", username)
                .getResultList().size() == 1;
    }

    public void create(User user) {
        this.entityManager.persist(user);
    }
}