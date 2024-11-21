package ru.taskmanagement.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.taskmanagement.dto.user.UserDto;
import ru.taskmanagement.dto.user.UserDtoShort;
import ru.taskmanagement.exceptions.TaskNotFoundException;
import ru.taskmanagement.exceptions.UserNotFoundException;
import ru.taskmanagement.models.Task;
import ru.taskmanagement.models.User;
import ru.taskmanagement.repositories.TaskRepository;
import ru.taskmanagement.repositories.UserRepository;
import ru.taskmanagement.services.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    @Override
    public List<UserDtoShort> getAllUsers() {
        return UserDtoShort.fromUser(userRepository.findAll());
    }

    @Override
    public UserDtoShort getUserById(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
        return UserDtoShort.fromUser(user);
    }

    @Override
    public void deleteUserById(long id) {
        userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));

        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        }
    }

    @Override
    public UserDto createUser(UserDto user) {
        List<Task> tasks = user.getTasksIds().stream()
                .map(taskId -> taskRepository.findById(taskId)
                        .orElseThrow(() -> new TaskNotFoundException("Task with id " + taskId + " not found")))
                .toList();

        return UserDto.fromUser(userRepository.save(
                User.builder()
                        .fullName(user.getFullName())
                        .username(user.getUserName())
                        .email(user.getEmail())
                        .password(user.getPassword())
                        .role(user.getRole())
                        .tasks(tasks)
                        .build()
        ));
    }

    @Override
    public UserDto updateUser(Long id, UserDto newData) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));

        List<Task> tasks = newData.getTasksIds().stream()
                .map(taskId -> taskRepository.findById(taskId)
                        .orElseThrow(() -> new TaskNotFoundException("Task with id " + taskId + " not found")))
                .collect(Collectors.toList());

        user.setFullName(newData.getFullName());
        user.setUsername(newData.getUserName());
        user.setEmail(newData.getEmail());
        user.setPassword(newData.getPassword());
        user.setRole(newData.getRole());
        user.setTasks(tasks);
        return UserDto.fromUser(userRepository.save(user));
    }
}
