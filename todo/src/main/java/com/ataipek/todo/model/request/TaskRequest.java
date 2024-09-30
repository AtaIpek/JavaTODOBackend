package com.ataipek.todo.model.request;

import com.ataipek.todo.entity.Task;
import com.ataipek.todo.enums.TaskStatus;
import com.ataipek.todo.exception.ApplicationException;
import com.ataipek.todo.model.response.TaskStatusGenerator;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskRequest {
    private final String title;
    private final String description;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    public static Task toEntity(TaskRequest taskRequest){
        if(taskRequest.getStartTime().isAfter(taskRequest.getEndTime())){
            throw new ApplicationException("Start Date Can't be After the End Date!");
        }
        if(taskRequest.getStartTime().isBefore(LocalDateTime.now())){
            throw new ApplicationException("Start Date Can't be Before the Current Date!");
        }
        if(taskRequest.getEndTime().isBefore(LocalDateTime.now())){
            throw new ApplicationException("End Date Can't be Before the Current Date!");
        }


        Task task = new Task();
        task.setTitle(taskRequest.getTitle()); //taskRequestten -> taske
        task.setDescription(taskRequest.getDescription());
        task.setStartTime(taskRequest.getStartTime());
        task.setEndTime(taskRequest.getEndTime());

        task.setTaskStatus(TaskStatusGenerator.statusGenerate(taskRequest.getStartTime(),taskRequest.getEndTime()));
        return task;
    }
}