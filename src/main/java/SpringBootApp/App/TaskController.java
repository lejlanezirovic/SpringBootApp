package SpringBootApp.App;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public List<TaskResponseDto> getALLTasks(){
        return taskService.getTasks();
    }
    @PostMapping
    public void AddTask(@RequestBody TaskRequestDto TRdto){
        taskService.AddTask(TRdto);
    }
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable String id){
        taskService.deleteTask(id);
    }
    @PutMapping("/{id}")
    public void UpdateTask(@RequestBody TaskRequestDto TRdto, @PathVariable String id){
        taskService.UpdateTask(TRdto, id);
    }
}
