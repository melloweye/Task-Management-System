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
public class TaskDtoShort {

    private String title;

    private String description;

    private String priority;

    private String status;

    private Long createdBy;

    private Long updatedBy;

    public static TaskDtoShort fromTask(Task task) {
        return TaskDtoShort.builder()
                .title(task.getTitle())
                .description(task.getDescription())
                .priority(task.getPriority())
                .status(task.getStatus())
                .createdBy(task.getCreatedBy())
                .updatedBy(task.getUpdatedBy())
                .build();
    }

    public static List<TaskDtoShort> fromTask(List<Task> tasks) {
        return tasks.stream()
                .map(TaskDtoShort::fromTask)
                .collect(Collectors.toList());
    }
}
