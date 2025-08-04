package SpringBootApp.App.Tasks.controllers;

import SpringBootApp.App.Tasks.entities.TaskEntity;
import SpringBootApp.App.Tasks.payloads.TaskRequestDto;
import SpringBootApp.App.Tasks.payloads.TaskResponseDto;


import SpringBootApp.App.Tasks.mappers.TaskMapper;

import SpringBootApp.App.Project.service.ProjectService;
import SpringBootApp.App.Tasks.service.TaskService;
import SpringBootApp.App.Tasks.payloads.TaskUserDto;
import SpringBootApp.App.User.service.UserService;
import com.sparktechcode.springcrud.controllers.multiple.SCRUDController;
import com.sparktechcode.springcrud.payloads.PathParams;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

@Getter
@RestController
@RequestMapping("users/{userId}/projects/{projectId}/tasks")
@RequiredArgsConstructor
public class TaskController implements
        SCRUDController<String, TaskRequestDto, TaskEntity, TaskResponseDto>{

    private final TaskService service;
    private final ProjectService projectService;
    private  final UserService userService;
    private final TaskMapper mapper;

    @Override
    public void onBeforeCreate(TaskRequestDto payload, TaskEntity entity, PathParams pathParams) {
       entity.setProjectEntity(projectService.findById(pathParams.get("projectId")));
       entity.setUser(userService.findById(pathParams.get("userId")));
    }
    @Override
    public Specification<TaskEntity> additionalFindFilter(PathParams pathParams) {
        return (root, query, builder) -> builder.equal(root.get("projectId"), pathParams.get("projectId"));
    }

   @PostMapping("/assign-task-to-user")
   public void assignTaskToUser(@RequestBody TaskUserDto payload){
      service.assignTaskToUser(payload);
  }
}
