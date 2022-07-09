package com.smalaca.cqrs.taskmanager.command.user;

import com.smalaca.taskamanager.exception.UserNotFoundException;
import com.smalaca.taskamanager.model.entities.User;
import com.smalaca.taskamanager.repository.UserRepository;

import java.util.Optional;

public class UserCommandRepository {
    private final UserRepository userRepository;

    public UserCommandRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    boolean notExistByFirstAndLastName(String firstName, String lastName) {
        return userRepository.findByUserNameFirstNameAndUserNameLastName(firstName, lastName).isEmpty();
    }

    Long save(User user) {
        User saved = userRepository.save(user);
        return saved.getId();
    }

    User findById(Long id) {
        Optional<User> found = userRepository.findById(id);

        if (found.isEmpty()) {
            throw new UserNotFoundException();
        }

        return found.get();
    }
}
