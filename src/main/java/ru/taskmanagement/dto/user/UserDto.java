package ru.taskmanagement.dto.user;

import jakarta.validation.constraints.Email;
import lombok.*;
import ru.taskmanagement.models.Task;
import ru.taskmanagement.models.User;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private String fullName;

    private String userName;

    private String password;

    @Email
    private String email;

    private String role;

    private List<Long> tasksIds;

    public static UserDto fromUser(User user) {
        return UserDto.builder()
                .fullName(user.getFullName())
                .userName(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .tasksIds(user.getTasks().stream()
                        .map(Task::getId)
                        .collect(Collectors.toList()))
                .build();
    }

    public static List<UserDto> fromUser(List<User> users) {
        return users.stream()
                .map(UserDto::fromUser)
                .collect(Collectors.toList());
    }
}
