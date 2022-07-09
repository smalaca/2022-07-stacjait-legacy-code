package com.smalaca.cqrs.taskmanager.command.team;

import com.smalaca.taskamanager.model.embedded.Codename;
import com.smalaca.taskamanager.model.entities.Team;

public class TeamDomainModel {
    private final Team team;

    public TeamDomainModel(Team team) {
        this.team = team;
    }

    static TeamDomainModel create(String name) {
        Team team = new Team();
        team.setName(name);
        return new TeamDomainModel(team);
    }

    void update(TeamUpdateCommand command) {
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
    }

    @Deprecated
    public Team getTeam() {
        return team;
    }
}
