package SpringBootApp.App;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    @RequestMapping("/tasks")
    public List<Task> getALLTasks(){
        return taskService.getAllTasks();
    }

    @RequestMapping(method = RequestMethod.POST, value="/tasks")
    public void AddTask(@RequestBody Task t){
        taskService.AddTask(t);
    }
    @RequestMapping(method = RequestMethod.DELETE, value="/tasks/delete-by-id/{id}")
    public void deleteTask(@PathVariable int id){
        taskService.deleteTask(id);
    }
}
