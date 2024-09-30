package com.ataipek.todo.model.response;

import com.ataipek.todo.enums.TaskStatus;
import com.ataipek.todo.model.request.TaskRequest;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TaskStatusGenerator {
    public static TaskStatus statusGenerate(LocalDateTime startDate, LocalDateTime endDate){
        if(startDate.isAfter(LocalDateTime.now())){
            return TaskStatus.UPCOMING;
        }
        if(startDate.isBefore(LocalDateTime.now())&&endDate.isAfter(LocalDateTime.now())){
            return TaskStatus.ONGOING;
        }
        if(endDate.isBefore(LocalDateTime.now())){
            return TaskStatus.OVERDUE;
        }
        return null;
    }
}