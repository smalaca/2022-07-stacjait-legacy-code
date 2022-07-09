package com.smalaca.cqrs.taskmanager.query.user;

import com.smalaca.taskamanager.dto.UserDto;
import com.smalaca.taskamanager.model.embedded.EmailAddress;
import com.smalaca.taskamanager.model.embedded.PhoneNumber;
import com.smalaca.taskamanager.model.entities.User;
import com.smalaca.taskamanager.model.enums.TeamRole;
import com.smalaca.taskamanager.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class UserQueryFacade {
    private final UserRepository userRepository;

    public UserQueryFacade(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDto> findAllUsers() {
        List<UserDto> usersDtos = new ArrayList<>();

        for (User user : userRepository.findAll()) {
            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setFirstName(user.getUserName().getFirstName());
            userDto.setLastName(user.getUserName().getLastName());
            userDto.setLogin(user.getLogin());
            userDto.setPassword(user.getPassword());

            TeamRole teamRole = user.getTeamRole();
            if (teamRole != null) {
                userDto.setTeamRole(teamRole.name());
            }

            PhoneNumber phoneNumber = user.getPhoneNumber();
            if (phoneNumber != null) {
                userDto.setPhonePrefix(phoneNumber.getPrefix());
                userDto.setPhoneNumber(phoneNumber.getNumber());
            }

            EmailAddress emailAddress = user.getEmailAddress();
            if (emailAddress != null) {
                userDto.setEmailAddress(emailAddress.getEmailAddress());
            }

            usersDtos.add(userDto);
        }
        return usersDtos;
    }

    public UserDto findById(Long id) {
        User updated = userRepository.findById(id).get();
        UserDto response = new UserDto();
        response.setId(updated.getId());
        response.setFirstName(updated.getUserName().getFirstName());
        response.setLastName(updated.getUserName().getLastName());
        response.setLogin(updated.getLogin());
        response.setPassword(updated.getPassword());

        TeamRole teamRole = updated.getTeamRole();
        if (teamRole != null) {
            response.setTeamRole(teamRole.name());
        }

        PhoneNumber phoneNumber = updated.getPhoneNumber();
        if (phoneNumber != null) {
            response.setPhonePrefix(phoneNumber.getPrefix());
            response.setPhoneNumber(phoneNumber.getNumber());
        }

        EmailAddress emailAddress = updated.getEmailAddress();
        if (emailAddress != null) {
            response.setEmailAddress(emailAddress.getEmailAddress());
        }
        return response;
    }
}
