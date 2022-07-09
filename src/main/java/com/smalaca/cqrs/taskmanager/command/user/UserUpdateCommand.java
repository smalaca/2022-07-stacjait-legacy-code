package com.smalaca.cqrs.taskmanager.command.user;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter(AccessLevel.PACKAGE)
public class UserUpdateCommand {
    private Long id;
    private String login;
    private String password;
    private String phoneNumber;
    private String phonePrefix;
    private String emailAddress;
    private String teamRole;
}
