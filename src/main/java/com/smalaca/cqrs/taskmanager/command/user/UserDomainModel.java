package com.smalaca.cqrs.taskmanager.command.user;

import com.smalaca.taskamanager.model.embedded.EmailAddress;
import com.smalaca.taskamanager.model.embedded.PhoneNumber;
import com.smalaca.taskamanager.model.entities.User;
import com.smalaca.taskamanager.model.enums.TeamRole;

public class UserDomainModel {
    private final User user;

    public UserDomainModel(User user) {
        this.user = user;
    }

    @Deprecated
    public User getUser() {
        return user;
    }

    void update(UserUpdateCommand command) {
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
    }
}
