package com.smalaca.cqrs.taskmanager.command.team;

import com.smalaca.taskamanager.dto.TeamDto;
import com.smalaca.taskamanager.model.embedded.Codename;
import com.smalaca.taskamanager.model.entities.Team;

import java.util.Optional;

public class TeamCommandFacade {
    private final TeamCommandRepository teamCommandRepository;

    public TeamCommandFacade(TeamCommandRepository teamCommandRepository) {
        this.teamCommandRepository = teamCommandRepository;
    }

    public Optional<Long> create(TeamDto teamDto) {
        Optional<Long> teamId = Optional.empty();

        if (teamCommandRepository.notExistsByName(teamDto.getName())) {
            Team team = new Team();
            team.setName(teamDto.getName());
            Long id = teamCommandRepository.save(team);

            teamId = Optional.of(id);
        }

        return teamId;
    }

    public void update(Long id, TeamDto teamDto) {
        Team team = teamCommandRepository.findById(id);

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

        teamCommandRepository.save(team);
    }
}
