package com.smalaca.cqrs.taskmanager.command.team;

import com.smalaca.taskamanager.model.embedded.Codename;
import com.smalaca.taskamanager.model.entities.Team;

import java.util.Optional;

public class TeamCommandFacade {
    private final TeamCommandRepository teamCommandRepository;

    public TeamCommandFacade(TeamCommandRepository teamCommandRepository) {
        this.teamCommandRepository = teamCommandRepository;
    }

    public Optional<Long> create(String name) {
        Optional<Long> teamId = Optional.empty();

        if (teamCommandRepository.notExistsByName(name)) {
            Team team = new Team();
            team.setName(name);
            Long id = teamCommandRepository.save(team);

            teamId = Optional.of(id);
        }

        return teamId;
    }

    public void update(TeamUpdateCommand command) {
        Team team = teamCommandRepository.findById(command.getId());

        if (command.getName() != null) {
            team.setName(command.getName());
        }

        if (command.getCodenameShort() != null && command.getCodenameFull() != null) {
            Codename codename = new Codename();
            codename.setShortName(command.getCodenameShort());
            codename.setFullName(command.getCodenameFull());
            team.setCodename(codename);
        }

        if (command.getDescription() != null) {
            team.setDescription(command.getDescription());
        }

        teamCommandRepository.save(team);
    }
}
