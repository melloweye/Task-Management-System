package ru.taskmanagement.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.taskmanagement.dto.task.TaskDto;
import ru.taskmanagement.dto.task.TaskDtoShort;
import ru.taskmanagement.services.TaskService;

import java.util.List;

@Tag(name = "Task API", description = "Allows to work with tasks")
@RequiredArgsConstructor
@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @Operation(summary = "Get all Tasks", description = "Returns list of all Tasks")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
    })
    @GetMapping
    public ResponseEntity<List<TaskDto>> getAllTasks(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(taskService.getAllTasks());
    }

    @Operation(summary = "Get Task by id", description = "Returns TAsk as per id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - Task was not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable("id") Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(taskService.getTaskById(id));
    }

    @Operation(summary = "Create Task", description = "Creates a new Task")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully created"),
    })
    @PostMapping("/add")
    public ResponseEntity<TaskDtoShort> createTask(@RequestBody TaskDtoShort task){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(taskService.createTask(task));
    }

    @Operation(summary = "Update existing Task by id", description = "Updates existing Task as per id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully updated"),
            @ApiResponse(responseCode = "404", description = "Not Found - Nothing to update")
    })
    @PutMapping("/{id}")
    public ResponseEntity<TaskDtoShort> updateTask(@PathVariable("id") Long id, @RequestBody TaskDtoShort newData){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(taskService.updateTask(id, newData));
    }

    @Operation(summary = "Delete Task by id", description = "Deletes Task as per id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Not Found - Nothing to delete")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteTask(@PathVariable("id") Long id) {
        taskService.deleteTaskById(id);
        return ResponseEntity.noContent().build();
    }
}
