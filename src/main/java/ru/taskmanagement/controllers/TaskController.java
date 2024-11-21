package ru.taskmanagement.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.taskmanagement.dto.task.TaskDto;
import ru.taskmanagement.dto.task.TaskDtoShort;
import ru.taskmanagement.services.TaskService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<List<TaskDto>> getAllTasks(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(taskService.getAllTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable("id") Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(taskService.getTaskById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<TaskDtoShort> createTask(@RequestBody TaskDtoShort task){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(taskService.createTask(task));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDtoShort> updateTask(@PathVariable("id") Long id, @RequestBody TaskDtoShort newData){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(taskService.updateTask(id, newData));
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable("id") Long id) {
        taskService.deleteTaskById(id);
    }
}
