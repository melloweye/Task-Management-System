package ru.taskmanagement.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.taskmanagement.dto.task.TaskDto;
import ru.taskmanagement.dto.task.TaskDtoShort;
import ru.taskmanagement.exceptions.TaskNotFoundException;
import ru.taskmanagement.exceptions.UserNotFoundException;
import ru.taskmanagement.models.Task;
import ru.taskmanagement.models.User;
import ru.taskmanagement.repositories.TaskRepository;
import ru.taskmanagement.repositories.UserRepository;
import ru.taskmanagement.services.TaskService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;


    @Override
    public List<TaskDto> getAllTasks() {
        return TaskDto.fromTask(taskRepository.findAll());
    }

    @Override
    public TaskDto getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task with id " + id + " not found"));
        return TaskDto.fromTask(task);
    }

    @Override
    public void deleteTaskById(Long id) {
        taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task with id " + id + " not found"));

        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
        }
    }

    @Override
    public TaskDtoShort createTask(TaskDtoShort task) {
        User user = userRepository.findById(task.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User with id " + task.getUserId() + " not found"));

        return TaskDtoShort.fromTask(taskRepository.save(
                Task.builder()
                        .title(task.getTitle())
                        .description(task.getDescription())
                        .priority(task.getPriority())
                        .status(task.getStatus())
                        .createdBy(user)
                        .updatedBy(task.getUpdatedBy())
                        .build()
        ));
    }

    @Override
    public TaskDtoShort updateTask(Long id, TaskDtoShort newData) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task with id " + id + " not found"));

        task.setTitle(newData.getTitle());
        task.setDescription(newData.getDescription());
        task.setPriority(newData.getPriority());
        task.setStatus(newData.getStatus());
        task.setCreatedBy(task.getCreatedBy());
        task.setUpdatedBy(newData.getUpdatedBy());

        return TaskDtoShort.fromTask(taskRepository.save(task));
    }
}
