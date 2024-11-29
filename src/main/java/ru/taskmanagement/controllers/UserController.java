package ru.taskmanagement.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.taskmanagement.dto.user.UserDto;
import ru.taskmanagement.dto.user.UserDtoShort;
import ru.taskmanagement.services.UserService;

import java.util.List;

@Tag(name = "User API", description = "Allows to work with users")
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;

    @Operation(summary = "Get all Users", description = "Returns list of all Users")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
    })
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserDtoShort>> getAllUsers() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.getAllUsers());
    }

    @Operation(summary = "Get User by id", description = "Returns User as per id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - User was not found")
    })
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UserDtoShort> getUserById(@PathVariable("id") Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.getUserById(id));
    }

    @Operation(summary = "Create User", description = "Creates a new User")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully created"),
    })
    @PostMapping("/add")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.createUser(userDto));
    }

    @Operation(summary = "Update existing User by id", description = "Updates existing User as per id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully updated"),
            @ApiResponse(responseCode = "404", description = "Not Found - Nothing to update")
    })
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long id, @RequestBody UserDto newData) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.updateUser(id, newData));
    }

    @Operation(summary = "Delete User by id", description = "Deletes User as per id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Not Found - Nothing to delete")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}
