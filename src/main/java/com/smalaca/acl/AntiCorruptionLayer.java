package com.smalaca.acl;

import com.smalaca.cqrs.taskmanager.command.user.UserCommandRepository;
import com.smalaca.cqrs.taskmanager.command.user.UserDomainModel;
import com.smalaca.cqrs.taskmanager.query.user.UserQueryRepository;
import com.smalaca.taskamanager.exception.UserNotFoundException;
import com.smalaca.taskamanager.model.entities.User;
import com.smalaca.taskamanager.repository.UserRepository;

import java.util.Optional;

public class AntiCorruptionLayer implements UserCommandRepository, UserQueryRepository {
    private final UserRepository userRepository;

    public AntiCorruptionLayer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean notExistByFirstAndLastName(String firstName, String lastName) {
        return userRepository.findByUserNameFirstNameAndUserNameLastName(firstName, lastName).isEmpty();
    }

    @Override
    public Long save(UserDomainModel user) {
        return save(user.getUser());
    }

    @Override
    public UserDomainModel findUserById(Long id) {
        return new UserDomainModel(findById(id));
    }

    @Override
    public Long save(User user) {
        User saved = userRepository.save(user);
        return saved.getId();
    }

    @Override
    public User findById(Long id) {
        Optional<User> found = userRepository.findById(id);

        if (found.isEmpty()) {
            throw new UserNotFoundException();
        }

        return found.get();
    }

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }
}
