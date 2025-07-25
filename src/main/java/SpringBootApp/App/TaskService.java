package SpringBootApp.App;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;

    public void addTask(TaskRequestDto payload){
        TaskEntity t= TaskMapper.toEntity(payload);
        UserEntity user=userRepository.findById(payload.getUserId())
                .orElseThrow(()-> new EntityNotFoundException("User nije pronadjen"));
        t.setUser(user); //ovo smo uradili van TaskMappera, kako bismo osigurali da sve sto treba da se nalazi u servisnom sloju ostane tu
        taskRepository.save(t);
    }
    public List<TaskResponseDto> getTasks(){
        List<TaskResponseDto> allResponses=new ArrayList<>();
        Iterable<TaskEntity> tasks=taskRepository.findAll();

        for(TaskEntity t: tasks){
            TaskResponseDto responseDTO=TaskMapper.toDto(t);
             allResponses.add(responseDTO);
        }
        return allResponses;
    }
    public void deleteTask(String id){
        taskRepository.deleteById(id);
    }
    public void updateTask(TaskRequestDto payload, String id){
        TaskEntity task=taskRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task nije pronadjen"));
        task.setTitle(payload.getTitle());
        task.setDescription(payload.getDescription());
        task.setStatus(payload.getStatus());
        taskRepository.save(task);
    }
    public void assignTaskToUser(TaskUserDto payload){
        TaskEntity task=taskRepository.findById(payload.getTaskId())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task nije pronadjen"));

        UserEntity user=userRepository.findById(payload.getUserId())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "User nije pronadjen"));
        //sada u taj task moram dodjeliti usera
        if(user!=null){
            task.setUser(user);
            taskRepository.save(task);
        }
        else{
            new EntityNotFoundException("user nije pronadjen");
        }
    }
}
