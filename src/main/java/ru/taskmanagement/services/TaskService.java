package ru.taskmanagement.services;
import ru.taskmanagement.dto.task.TaskDto;
import ru.taskmanagement.dto.task.TaskDtoShort;

import java.util.List;

public interface TaskService {

    List<TaskDto> getAllTasks();

    TaskDto getTaskById(Long id);

    void deleteTaskById(Long id);

    TaskDtoShort createTask(TaskDtoShort task);

    TaskDtoShort updateTask(Long id, TaskDtoShort newData);

}
