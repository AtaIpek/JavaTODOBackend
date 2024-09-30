package com.ataipek.todo.controller;

import com.ataipek.todo.enums.TaskStatus;
import com.ataipek.todo.model.request.TaskRequest;
import com.ataipek.todo.model.response.TaskResponse;
import com.ataipek.todo.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //dünyaya açar
@RequestMapping("/task") //task yazılınca buraya gelsin pointing
@AllArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping //task yazıldıktan sonra get yazılınca buraya gelsin pointing
    public List<TaskResponse> getTasks(){
        return taskService.getTasks();
    }

    @GetMapping("/by-title/{title}")
    public List<TaskResponse> getTasksByTitle(@PathVariable String title){ //bu title input title
        return taskService.getTasksByTitle(title); //bu title input (aynı) buraya koyuyor ve Servicea gönderiyor
    }

    @GetMapping("/by-description/{description}") //süslü parantez pathvariable
    public List<TaskResponse> getTasksByDescription(@PathVariable String description){
        return taskService.getTasksByDescription(description);
    }

    @GetMapping("/by-status/{status}")
    public List<TaskResponse> getTasksByStatus(@PathVariable TaskStatus status){
        return taskService.getTasksByStatus(status);
    }

    @PostMapping("/create")
    public TaskResponse createTask(@RequestBody TaskRequest taskRequest){
        return taskService.createTask(taskRequest);
    }

    @PutMapping("/complete/{taskId}")
    public TaskResponse completeTask(@PathVariable Long taskId){
        return taskService.completeTask(taskId);
    }



}