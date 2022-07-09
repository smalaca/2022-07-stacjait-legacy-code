package com.smalaca.cqrs.taskmanager.command.team;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TeamUpdateCommand {
    private final Long id;
    private final String name;
    private final String codenameShort;
    private final String codenameFull;
    private final String description;
}
