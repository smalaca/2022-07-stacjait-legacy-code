package com.smalaca.cqrs.taskmanager.command.user;

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

    public void update(UserUpdateCommand command) {
        User user = userCommandRepository.findById(command.getId());

        if (command.getLogin() != null) {
            user.setLogin(command.getLogin());
        }

        if (command.getPassword() != null) {
            user.setPassword(command.getPassword());
        }

        if (command.getPhoneNumber() != null) {
            PhoneNumber phoneNumber = new PhoneNumber();
            phoneNumber.setPrefix(command.getPhonePrefix());
            phoneNumber.setNumber(command.getPhoneNumber());
            user.setPhoneNumber(phoneNumber);
        }

        if (command.getEmailAddress() != null) {
            EmailAddress emailAddress = new EmailAddress();
            emailAddress.setEmailAddress(command.getEmailAddress());
            user.setEmailAddress(emailAddress);
        }

        if (command.getTeamRole() != null) {
            user.setTeamRole(TeamRole.valueOf(command.getTeamRole()));
        }

        userCommandRepository.save(user);
    }
}
