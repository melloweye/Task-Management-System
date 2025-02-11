package ru.taskmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.taskmanagement.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
