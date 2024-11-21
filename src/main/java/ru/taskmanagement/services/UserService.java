package ru.taskmanagement.services;

import ru.taskmanagement.dto.user.UserDto;
import ru.taskmanagement.dto.user.UserDtoShort;
import ru.taskmanagement.models.User;

import java.util.List;

public interface UserService {

    List<UserDtoShort> getAllUsers();

    UserDtoShort getUserById(long id);

    void deleteUserById(long id);

    UserDto createUser(UserDto user);

    UserDto updateUser(Long id, UserDto newData);
}
