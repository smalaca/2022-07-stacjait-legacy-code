package com.smalaca.cqrs.taskmanager.command.team;

import java.util.Optional;

public class TeamCommandFacade {
    private final TeamCommandRepository teamCommandRepository;

    public TeamCommandFacade(TeamCommandRepository teamCommandRepository) {
        this.teamCommandRepository = teamCommandRepository;
    }

    public Optional<Long> create(String name) {
        if (teamCommandRepository.notExistsByName(name)) {
            TeamDomainModel teamDomainModel = TeamDomainModel.create(name);
            Long id = teamCommandRepository.save(teamDomainModel);

            return Optional.of(id);
        } else {
            return Optional.empty();
        }
    }

    public void update(TeamUpdateCommand command) {
        TeamDomainModel teamDomainModel = teamCommandRepository.findDomainModelById(command.getId());
        teamDomainModel.update(command);

        teamCommandRepository.save(teamDomainModel);
    }
}
