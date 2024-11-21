package ru.taskmanagement.dto.task;

import lombok.*;
import ru.taskmanagement.models.Task;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDto {

    private Long id;

    private String title;

    private String description;

    private String priority;

    private String status;

    private Long createdBy;

    private Long updatedBy;

    public static TaskDto fromTask(Task task) {
        return TaskDto.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .priority(task.getPriority())
                .status(task.getStatus())
                .createdBy(task.getCreatedBy())
                .updatedBy(task.getUpdatedBy())
                .build();
    }

    public static List<TaskDto> fromTask(List<Task> tasks) {
        return tasks.stream()
                .map(TaskDto::fromTask)
                .collect(Collectors.toList());
    }
}
