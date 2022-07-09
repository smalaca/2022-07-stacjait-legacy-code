package com.smalaca.cqrs.taskmanager.query.team;

import com.smalaca.taskamanager.api.rest.TeamController;
import com.smalaca.taskamanager.dto.TeamDto;
import com.smalaca.taskamanager.model.entities.Team;
import com.smalaca.taskamanager.repository.TeamRepository;

import java.util.List;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

public class TeamQueryFacade {
    private final TeamRepository teamRepository;

    public TeamQueryFacade(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<TeamDto> findAllTeams() {
        return StreamSupport.stream(teamRepository.findAll().spliterator(), false)
                .map(team -> {
                    TeamDto dto = new TeamDto();
                    dto.setId(team.getId());
                    dto.setName(team.getName());

                    if (team.getCodename() != null) {
                        dto.setCodenameShort(team.getCodename().getShortName());
                        dto.setCodenameFull(team.getCodename().getFullName());
                    }

                    dto.setDescription(team.getDescription());

                    return dto;
                })
                .collect(toList());
    }

    public TeamDto findTeamById(Long id) {
        Team updated = teamRepository.findById(id).get();
        TeamDto dto = new TeamDto();
        dto.setId(updated.getId());
        dto.setName(updated.getName());
        if (updated.getCodename() != null) {
            dto.setCodenameShort(updated.getCodename().getShortName());
            dto.setCodenameFull(updated.getCodename().getFullName());
        }

        dto.setDescription(updated.getDescription());
        return dto;
    }
}
