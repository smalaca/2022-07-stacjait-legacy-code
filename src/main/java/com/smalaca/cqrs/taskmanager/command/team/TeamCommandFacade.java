package com.smalaca.cqrs.taskmanager.command.team;

import com.smalaca.taskamanager.dto.TeamDto;
import com.smalaca.taskamanager.exception.TeamNotFoundException;
import com.smalaca.taskamanager.model.embedded.Codename;
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

    public void update(Long id, TeamDto teamDto) {
        Optional<Team> team1 = teamRepository.findById(id);

        if (team1.isEmpty()) {
            throw new TeamNotFoundException();
        }

        Team team = team1.get();

        if (teamDto.getName() != null) {
            team.setName(teamDto.getName());
        }

        if (teamDto.getCodenameShort() != null && teamDto.getCodenameFull() != null) {
            Codename codename = new Codename();
            codename.setShortName(teamDto.getCodenameShort());
            codename.setFullName(teamDto.getCodenameFull());
            team.setCodename(codename);
        }

        if (teamDto.getDescription() != null) {
            team.setDescription(teamDto.getDescription());
        }

        teamRepository.save(team);
    }
}
