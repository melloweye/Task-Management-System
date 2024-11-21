package ru.taskmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.taskmanagement.models.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
