package com.smalaca.cqrs.taskmanager.command.user;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter(AccessLevel.PACKAGE)
public class UserCreateCommand {
    private String firstName;
    private String lastName;
    private String teamRole;
    private String login;
    private String password;
}
