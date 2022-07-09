package com.smalaca.acl;

import com.smalaca.cqrs.taskmanager.command.team.TeamCommandRepository;
import com.smalaca.cqrs.taskmanager.query.team.TeamQueryRepository;
import com.smalaca.taskamanager.exception.TeamNotFoundException;
import com.smalaca.taskamanager.model.entities.Team;
import com.smalaca.taskamanager.repository.TeamRepository;

import java.util.Optional;

public class TaskManagerAntiCorruptionLayer implements TeamCommandRepository, TeamQueryRepository {
    private final TeamRepository teamRepository;

    public TaskManagerAntiCorruptionLayer(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public boolean notExistsByName(String name) {
        return teamRepository.findByName(name).isEmpty();
    }

    @Override
    public Long save(Team team) {
        return teamRepository.save(team).getId();
    }

    @Override
    public Team findById(Long id) {
        Optional<Team> found = teamRepository.findById(id);

        if (found.isEmpty()) {
            throw new TeamNotFoundException();
        }

        return found.get();
    }

    @Override
    public Iterable<Team> findAll() {
        return teamRepository.findAll();
    }
}
