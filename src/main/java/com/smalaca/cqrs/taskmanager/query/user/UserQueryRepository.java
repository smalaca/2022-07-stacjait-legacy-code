package com.smalaca.cqrs.taskmanager.query.user;

import com.smalaca.taskamanager.model.entities.User;

public interface UserQueryRepository {
    Iterable<User> findAll();

    User findById(Long id);
}
