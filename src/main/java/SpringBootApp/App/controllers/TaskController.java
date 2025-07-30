package SpringBootApp.App.controllers;

import SpringBootApp.App.payloads.TaskRequestDto;
import SpringBootApp.App.payloads.TaskResponseDto;
import SpringBootApp.App.services.TaskService;
import SpringBootApp.App.payloads.TaskUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public List<TaskResponseDto> getALLTasks(){
        return taskService.getTasks();
    }
    @PostMapping
    public void addTask(@RequestBody TaskRequestDto payload){
        taskService.addTask(payload);
    }
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable String id){
        taskService.deleteTask(id);
    }
    @PutMapping("/{id}")
    public void updateTask(@RequestBody TaskRequestDto payload, @PathVariable String id){
        taskService.updateTask(payload, id);
    }
    @PostMapping("/assign-task-to-user")
    public void assignTaskToUser(@RequestBody TaskUserDto payload){
        taskService.assignTaskToUser(payload);
    }
}
