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
public class UserDtoShort {

    private Long id;

    private String fullName;

    private String userName;

    @Email
    private String email;

    private String role;

    private List<Task> tasks;

    public static UserDtoShort fromUser(User user) {
        return UserDtoShort.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .userName(user.getUsername())
                .email(user.getEmail())
                .role(String.valueOf(user.getRole()))
                .tasks(user.getTasks())
                .build();
    }

    public static List<UserDtoShort> fromUser(List<User> users) {
        return users.stream()
                .map(UserDtoShort::fromUser)
                .collect(Collectors.toList());
    }
}
