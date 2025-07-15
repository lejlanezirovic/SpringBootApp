package SpringBootApp.App;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    //dodaj task, svi taskovi, obrisi
    public void AddTask(Task task){
        taskRepository.save(task);
    }
    public List<Task>  getAllTasks(){
        List<Task> tasks=new ArrayList<>();
        taskRepository.findAll().forEach(tasks::add);
        return tasks;
    }
    public void deleteTask(int id){
        taskRepository.deleteById(id);
    }
}
