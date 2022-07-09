package com.smalaca.cqrs.taskmanager.query.team;

import com.smalaca.taskamanager.model.entities.Team;

public interface TeamQueryRepository {
    Iterable<Team> findAll();

    Team findById(Long id);
}
