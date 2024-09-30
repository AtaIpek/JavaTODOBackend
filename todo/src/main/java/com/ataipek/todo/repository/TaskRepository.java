package com.ataipek.todo.repository;

import com.ataipek.todo.entity.Task;
import com.ataipek.todo.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByTitle(String title); //Custom data çekme
    List<Task> findAllByDescription(String description);
    List<Task> findAllByTaskStatus(TaskStatus status);
}