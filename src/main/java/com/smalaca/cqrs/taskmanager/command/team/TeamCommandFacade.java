package com.smalaca.cqrs.taskmanager.command.team;

import com.smalaca.taskamanager.dto.TeamDto;
import com.smalaca.taskamanager.model.entities.Team;
import com.smalaca.taskamanager.repository.TeamRepository;

import java.util.Optional;

public class TeamCommandFacade {
    private final TeamRepository teamRepository;

    public TeamCommandFacade(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Optional<Long> create(TeamDto teamDto) {
        boolean doesNotExist = teamRepository.findByName(teamDto.getName()).isPresent();
        Optional<Long> teamId = Optional.empty();

        if (!doesNotExist) {
            Team team = new Team();
            team.setName(teamDto.getName());
            Team saved = teamRepository.save(team);

            teamId = Optional.of(saved.getId());
        }
        return teamId;
    }
}
