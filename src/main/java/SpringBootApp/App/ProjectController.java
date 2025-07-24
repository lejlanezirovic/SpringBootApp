package SpringBootApp.App;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping
    public List<ProjectEntity> getAllProjects(){
      return  projectService.getAllProjects();
    }

    @PostMapping
    public void addProjects(@RequestBody ProjectRequestDto projectDto){
        projectService.AddProjects(projectDto);
    }

    @PostMapping("/add-task/{id}")
    public void addTaskForProject(@PathVariable String id, @RequestBody TaskRequestDto taskDto){
        projectService.addTaskForProject(id, taskDto);
    }

    @PutMapping("/{projectId}/update-task/{taskId}")
    public void updateTaskFromProject(@PathVariable String projectId, @PathVariable String taskId, @RequestBody TaskRequestDto taskDTO){
        projectService.updateTaskForProject(projectId,taskId,taskDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteProject(String id){
        projectService.deleteProjectById(id);
    }
    @DeleteMapping("/{projectId}/remove-task/{taskId}")
    public void deleteTaskFromProject(@PathVariable String projectId, @PathVariable String taskId){
        projectService.deleteTaskFromProject(projectId,taskId);
    }
}
