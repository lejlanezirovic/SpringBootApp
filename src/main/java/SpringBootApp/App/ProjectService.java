package SpringBootApp.App;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TaskRepository taskRepository;
    //omoguciti dodavanje novih projekata, brisanje projekata, getAllProjects, dodaj Task za projekat

    //getallProjects
    public List<ProjectEntity> getAllProjects(){
        List<ProjectEntity> projects=new ArrayList<>();
        projectRepository.findAll().forEach(projects::add);
        return projects;
    }

   public void AddProjects(ProjectRequestDto projectDto){
        ProjectEntity projectEntity=new ProjectEntity();
        projectEntity.setTitle(projectDto.getTitle());
        projectRepository.save(projectEntity);
   }
   public void deleteProjectById(String id){
        projectRepository.deleteById(id);
   }

    public void updateTaskForProject(String projectId, String taskId, TaskRequestDto taskDTO){
        ProjectEntity project=projectRepository.findById(projectId)
                .orElseThrow(()-> new EntityNotFoundException("projekat sa id "+ projectId+ " nije pronadjen"));

        for(TaskEntity t: project.getTasks()){
            if(t.getId().equals(taskId)){
                t.setTitle(taskDTO.getTitle());
                t.setDescription(taskDTO.getDescription());
                t.setStatus(taskDTO.getStatus());
                t.setProjectEntity(project);
                taskRepository.save(t);
            }
        }
        projectRepository.save(project);
    }

    public void addTaskForProject(String id, TaskRequestDto taskDto){
        TaskEntity task=new TaskEntity();

        ProjectEntity projectEntity=projectRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("projekat sa id "+ id+ " nije pronadjen"));

        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setStatus(taskDto.getStatus());

        task.setProjectEntity(projectEntity);
        projectEntity.getTasks().add(task);
        taskRepository.save(task);
        projectRepository.save(projectEntity);
    }

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
