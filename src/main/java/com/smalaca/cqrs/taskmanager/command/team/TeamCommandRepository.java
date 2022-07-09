package com.smalaca.cqrs.taskmanager.command.team;

public interface TeamCommandRepository {
    boolean notExistsByName(String name);
}
