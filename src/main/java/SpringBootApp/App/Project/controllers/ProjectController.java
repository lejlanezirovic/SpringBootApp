package SpringBootApp.App.Project.controllers;

import SpringBootApp.App.Project.entities.ProjectEntity;

import SpringBootApp.App.User.entities.UserEntity;
import SpringBootApp.App.Project.mappers.ProjectMapper;
import SpringBootApp.App.Project.payloads.ProjectRequestDto;
import SpringBootApp.App.Project.payloads.ProjectResponseDto;
import SpringBootApp.App.Project.service.ProjectService;
import SpringBootApp.App.User.service.UserService;
import com.sparktechcode.springcrud.controllers.multiple.SCRUDController;
import com.sparktechcode.springcrud.payloads.PathParams;
import jakarta.persistence.criteria.Join;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

@Getter
@RestController
@RequestMapping("users/{userId}/projects")
@RequiredArgsConstructor
public class ProjectController implements
        SCRUDController<String, ProjectRequestDto, ProjectEntity, ProjectResponseDto> {

    private final ProjectService service;
    private final UserService userService;
    private final ProjectMapper mapper;

    @Override
    public void onBeforeCreate(ProjectRequestDto payload, ProjectEntity entity, PathParams pathParams) {
        String userId=pathParams.get("userId");
        UserEntity user=userService.findById(userId);
        entity.getParticipants().add(user);
        user.getProjects().add(entity);
    }

    @Override
    public Specification<ProjectEntity> additionalFindFilter(PathParams pathParams) {
        return (root, query, builder) ->
        {
            Join<ProjectEntity, UserEntity> join=root.join("participants");
            return builder.equal(join.get("id"), pathParams.get("userId"));
        };
    }

//    @PostMapping("/add-task/{id}")
//    public void addTaskForProject(@PathVariable String id, @RequestBody TaskRequestDto taskDto){
//        service.addTaskForProject(id, taskDto);
//    }

    @DeleteMapping("/{projectId}/remove-task/{taskId}")
    public void deleteTaskFromProject(@PathVariable String projectId, @PathVariable String taskId){
        service.deleteTaskFromProject(projectId,taskId);
    }
}
