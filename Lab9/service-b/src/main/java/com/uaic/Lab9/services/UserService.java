package com.uaic.Lab9.services;

import com.uaic.Lab9.dtos.RegistrationDto;
import com.uaic.Lab9.entities.Group;
import com.uaic.Lab9.entities.User;
import com.uaic.Lab9.exceptions.EntityNotFoundException;
import com.uaic.Lab9.repositories.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Optional;

@ApplicationScoped
public class UserService implements Serializable {
    @Inject
    private UserRepository userRepository;

    @Inject
    private GroupService groupService;

    public User getByUsernameAndGroup(String username, String groupName) {
        Optional<User> user = userRepository.getByUsername(username);
        if (!user.isPresent()) {
            throw new EntityNotFoundException("User");
        }
        return user.get();
    }

    public void register(RegistrationDto registrationDto) {
        String hashedPassword = DigestUtils.sha256Hex(registrationDto.getPassword());
        User user = new User(registrationDto.getUsername(), hashedPassword);
        Group group = groupService.getByName(registrationDto.getGroup());
        user.addGroup(group);
        userRepository.create(user);
    }
}
