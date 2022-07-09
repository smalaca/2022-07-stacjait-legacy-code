package com.smalaca.cqrs.taskmanager.command.user;

import com.smalaca.taskamanager.api.rest.UserController;
import com.smalaca.taskamanager.dto.UserDto;
import com.smalaca.taskamanager.model.embedded.UserName;
import com.smalaca.taskamanager.model.entities.User;
import com.smalaca.taskamanager.model.enums.TeamRole;
import com.smalaca.taskamanager.repository.UserRepository;

import java.util.Optional;

public class UserCommandFacade {
    private final UserRepository userRepository;

    public UserCommandFacade(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<Long> create(UserDto userDto) {
        boolean doesNotExist = !userRepository.findByUserNameFirstNameAndUserNameLastName(userDto.getFirstName(), userDto.getLastName()).isEmpty();
        Optional<Long> id = Optional.empty();

        if (!doesNotExist) {
            User user = new User();
            user.setTeamRole(TeamRole.valueOf(userDto.getTeamRole()));
            UserName userName = new UserName();
            userName.setFirstName(userDto.getFirstName());
            userName.setLastName(userDto.getLastName());
            user.setUserName(userName);
            user.setLogin(userDto.getLogin());
            user.setPassword(userDto.getPassword());

            User saved = userRepository.save(user);
            id = Optional.of(saved.getId());
        }
        return id;
    }
}
