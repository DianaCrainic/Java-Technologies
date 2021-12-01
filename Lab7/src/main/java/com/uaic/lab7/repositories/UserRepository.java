package com.uaic.lab7.repositories;

import com.uaic.lab7.entities.Admin;
import com.uaic.lab7.entities.Author;
import com.uaic.lab7.entities.User;

import javax.ejb.Stateless;
import javax.transaction.Transactional;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Stateless
public class UserRepository extends DataRepository {
    public User getByUsernameAndPassword(String username, String password) throws NoSuchAlgorithmException {
        String hashedPassword = makeHash(password);
        return (User) this.entityManager.createNamedQuery("User.getByUsernameAndPassword")
                .setParameter("username", username)
                .setParameter("password", hashedPassword)
                .getSingleResult();
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void create(User user, String role) throws NoSuchAlgorithmException {
        String hashedPassword = makeHash(user.getPassword());
        if (role.equals("admin")) {
            Admin admin = new Admin(user.getUsername(), hashedPassword);
            this.entityManager.persist(admin);
        } else {
            Author author = new Author(user.getUsername(), hashedPassword);
            this.entityManager.persist(author);
        }
    }

    private static String makeHash(String input) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(input.getBytes());
        return new String(messageDigest.digest());
    }
}