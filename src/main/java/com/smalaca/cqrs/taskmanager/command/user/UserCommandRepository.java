package com.smalaca.cqrs.taskmanager.command.user;

import com.smalaca.taskamanager.model.entities.User;

public interface UserCommandRepository {
    boolean notExistByFirstAndLastName(String firstName, String lastName);

    Long save(User user);

    Long save(UserDomainModel user);

    UserDomainModel findUserById(Long id);
}
