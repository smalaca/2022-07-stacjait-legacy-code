package com.smalaca.acl;

import com.smalaca.cqrs.taskmanager.command.team.TeamCommandRepository;
import com.smalaca.taskamanager.repository.TeamRepository;

public class TaskManagerAntiCorruptionLayer implements TeamCommandRepository {
    private final TeamRepository teamRepository;

    public TaskManagerAntiCorruptionLayer(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public boolean notExistsByName(String name) {
        return teamRepository.findByName(name).isEmpty();
    }
}
