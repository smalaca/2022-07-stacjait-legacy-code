package com.smalaca.cqrs.taskmanager.command.team;

import com.smalaca.taskamanager.model.entities.Team;

public interface TeamCommandRepository {
    boolean notExistsByName(String name);

    Long save(Team team);

    Team findById(Long id);
}
