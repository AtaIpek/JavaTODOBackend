package com.ataipek.todo.model.response;

import com.ataipek.todo.entity.Task;
import com.ataipek.todo.enums.TaskStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data //amacı sadece bilgi taşımak
public class TaskResponse {
    private final Long id;
    private final String title;
    private final String description;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final TaskStatus taskStatus;

    public static TaskResponse fromEntity(Task task){
        TaskStatus generatedStatus = TaskStatusGenerator.statusGenerate(task.getStartTime(), task.getEndTime());
        return new TaskResponse(task.getId(),task.getTitle(), task.getDescription(), task.getStartTime(), task.getEndTime(),generatedStatus);
    }
}