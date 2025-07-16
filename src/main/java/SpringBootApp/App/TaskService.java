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

    public void AddTask(TaskRequestDto trDTO){
        TaskEntity t= TaskMapper.toEntity(trDTO);
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

    public void UpdateTask(TaskRequestDto trDTO, String id){
        TaskEntity task=taskRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task nije pronadjen"));
        task.setTitle(trDTO.getTitle());
        task.setDescription(trDTO.getDescription());
        task.setStatus(trDTO.getStatus());
        taskRepository.save(task);
    }
}
