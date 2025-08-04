package SpringBootApp.App.Project.service;


import SpringBootApp.App.Project.repository.ProjectRepository;
import SpringBootApp.App.Tasks.payloads.TaskRequestDto;
import SpringBootApp.App.Project.entities.ProjectEntity;
import SpringBootApp.App.Tasks.entities.TaskEntity;
import SpringBootApp.App.Tasks.repository.TaskRepository;
import com.sparktechcode.springcrud.services.CrudService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Getter
@Service
@RequiredArgsConstructor
public class ProjectService implements CrudService<String, ProjectEntity> {

    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;
    private final EntityManager entityManager;

    public void deleteTaskFromProject(String projectId, String taskId){
        ProjectEntity projectEntity=projectRepository.findById(projectId)
                .orElseThrow(()-> new EntityNotFoundException("projekat sa id "+ projectId+ " nije pronadjen"));

        TaskEntity taskToRemove=null;
        for(TaskEntity t:projectEntity.getTasks()){
            if(t.getId().equals(taskId)){
                taskToRemove=t;
                break;
            }
        }
        projectEntity.getTasks().remove(taskToRemove);
        taskToRemove.setProjectEntity(null);//da uklonim referenciranje na projekat iz kojeg ga brisem
        projectRepository.save(projectEntity);
    }
}
