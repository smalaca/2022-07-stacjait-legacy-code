package com.smalaca.cqrs.taskmanager.command.user;

import com.smalaca.taskamanager.dto.UserDto;
import com.smalaca.taskamanager.model.embedded.EmailAddress;
import com.smalaca.taskamanager.model.embedded.PhoneNumber;
import com.smalaca.taskamanager.model.embedded.UserName;
import com.smalaca.taskamanager.model.entities.User;
import com.smalaca.taskamanager.model.enums.TeamRole;

import java.util.Optional;

public class UserCommandFacade {
    private final UserCommandRepository userCommandRepository;

    public UserCommandFacade(UserCommandRepository userCommandRepository) {
        this.userCommandRepository = userCommandRepository;
    }

    public Optional<Long> create(UserCreateCommand command) {
        if (userCommandRepository.notExistByFirstAndLastName(command.getFirstName(), command.getLastName())) {
            User user = new User();
            user.setTeamRole(TeamRole.valueOf(command.getTeamRole()));
            UserName userName = new UserName();
            userName.setFirstName(command.getFirstName());
            userName.setLastName(command.getLastName());
            user.setUserName(userName);
            user.setLogin(command.getLogin());
            user.setPassword(command.getPassword());

            Long id = userCommandRepository.save(user);
            return Optional.of(id);
        } else {
            return Optional.empty();
        }
    }

    public void update(Long id, UserDto userDto) {
        User user = userCommandRepository.findById(id);

        if (userDto.getLogin() != null) {
            user.setLogin(userDto.getLogin());
        }

        if (userDto.getPassword() != null) {
            user.setPassword(userDto.getPassword());
        }

        if (userDto.getPhoneNumber() != null) {
            PhoneNumber phoneNumber = new PhoneNumber();
            phoneNumber.setPrefix(userDto.getPhonePrefix());
            phoneNumber.setNumber(userDto.getPhoneNumber());
            user.setPhoneNumber(phoneNumber);
        }

        if (userDto.getEmailAddress() != null) {
            EmailAddress emailAddress = new EmailAddress();
            emailAddress.setEmailAddress(userDto.getEmailAddress());
            user.setEmailAddress(emailAddress);
        }

        if (userDto.getTeamRole() != null) {
            user.setTeamRole(TeamRole.valueOf(userDto.getTeamRole()));
        }

        userCommandRepository.save(user);
    }
}
