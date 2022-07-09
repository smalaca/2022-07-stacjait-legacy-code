package com.smalaca.cqrs.taskmanager.command.user;

import com.smalaca.taskamanager.dto.UserDto;
import com.smalaca.taskamanager.exception.UserNotFoundException;
import com.smalaca.taskamanager.model.embedded.EmailAddress;
import com.smalaca.taskamanager.model.embedded.PhoneNumber;
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

    public void update(Long id, UserDto userDto) {
        Optional<User> user1;
        user1 = userRepository.findById(id);

        if (user1.isEmpty()) {
            throw new UserNotFoundException();
        }

        User user = user1.get();

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

        userRepository.save(user);
    }
}
