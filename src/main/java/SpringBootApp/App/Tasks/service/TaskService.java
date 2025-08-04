package SpringBootApp.App.Tasks.service;

import SpringBootApp.App.Tasks.payloads.TaskUserDto;
import SpringBootApp.App.Tasks.entities.TaskEntity;
import SpringBootApp.App.User.entities.UserEntity;
import SpringBootApp.App.Tasks.repository.TaskRepository;
import SpringBootApp.App.User.repository.UserRepository;
import com.sparktechcode.springcrud.services.CrudService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Getter
@Service
@RequiredArgsConstructor
public class TaskService implements CrudService<String, TaskEntity> {

    private  final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final EntityManager entityManager; //ovo se dodaje u konstruktor TaskService ali mi imamo anotaciju RequiredArgsConstructor


    public void assignTaskToUser(TaskUserDto payload){
        TaskEntity task=taskRepository.findById(payload.getTaskId())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task nije pronadjen"));
        UserEntity user=userRepository.findById(payload.getUserId())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "User nije pronadjen"));
        if(user!=null){
            task.setUser(user);
            taskRepository.save(task);
        }
        else{
            new EntityNotFoundException("user nije pronadjen");
        }
    }
}
