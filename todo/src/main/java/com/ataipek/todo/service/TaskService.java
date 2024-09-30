package com.ataipek.todo.service;

import com.ataipek.todo.entity.Task;
import com.ataipek.todo.enums.TaskStatus;
import com.ataipek.todo.exception.ApplicationException;
import com.ataipek.todo.model.request.TaskRequest;
import com.ataipek.todo.model.response.TaskResponse;
import com.ataipek.todo.model.response.TaskStatusGenerator;
import com.ataipek.todo.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springdoc.core.service.SecurityService;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final SecurityService securityParser;

//    public List<TaskResponse> getTasks(){
//        List<Task> tasks = taskRepository.findAll();
//        List<TaskResponse> taskResponses = new ArrayList<>();
//        for (Task task : tasks) {
//            TaskResponse taskResponse = new TaskResponse(task.getTitle(),task.getDescription());
//            taskResponses.add(taskResponse);
//        }
//        return taskResponses;
//    } maplemek convert eder, taskEntity sadece ID için

    public List<TaskResponse> getTasks(){
        return taskRepository.findAll().stream().map(TaskResponse::fromEntity).toList();
    }

    public List<TaskResponse> getTasksByTitle(String title){
        return taskRepository.findAllByTitle(title).stream().map(TaskResponse::fromEntity).toList();
    }

    public List<TaskResponse> getTasksByDescription(String description){
        return taskRepository.findAllByDescription(description).stream().map(TaskResponse::fromEntity).toList();
    }

    public List<TaskResponse> getTasksByStatus(TaskStatus status){
        List<Task> tasks = taskRepository.findAll();
        List<Task> chosenTasks = new ArrayList<>();
        for (Task task : tasks) {
            TaskResponse taskResponse = TaskResponse.fromEntity(task);
            if(taskResponse.getTaskStatus() == status){
                chosenTasks.add(task);
            }
        }
        return chosenTasks.stream().map(TaskResponse::fromEntity).toList();
    }

    public TaskResponse createTask(TaskRequest taskRequest){
        Task task = TaskRequest.toEntity(taskRequest);
        Task taskEntity = taskRepository.save(task); //taskEntity sadece ID için
        return TaskResponse.fromEntity(taskEntity); //taskEntityden -> Responsa
    }

    public TaskResponse completeTask(Long taskId){
        Task currentTask = taskRepository.findById(taskId).orElseThrow(()->new ApplicationException("Task Not Found!"));
        currentTask.setTaskStatus(TaskStatus.COMPLETED);
        Task taskEntity = taskRepository.save(currentTask);
        return TaskResponse.fromEntity(taskEntity);
    }
}