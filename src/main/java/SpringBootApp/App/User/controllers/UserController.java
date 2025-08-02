package SpringBootApp.App.User.controllers;

import SpringBootApp.App.User.entities.UserEntity;
import SpringBootApp.App.User.mappers.UserMapper;
import SpringBootApp.App.User.payloads.UserProjectRequestDto;
import SpringBootApp.App.User.payloads.UserRequestDto;
import SpringBootApp.App.User.payloads.UserResponseDto;
import SpringBootApp.App.User.service.UserService;
import com.sparktechcode.springcrud.controllers.multiple.SCRUDController;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Getter
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController implements
        SCRUDController<String, UserRequestDto, UserEntity, UserResponseDto> {

    private final UserService service;
    private final UserMapper mapper;

    @PostMapping("/{id}/add-project")
    public void addExistingProjectToUser(@RequestBody UserProjectRequestDto userProjectDto, @PathVariable String id){
        service.addExistingProjectToUser(userProjectDto, id);
    }

//    @PostMapping("/create-new-project/{id}")
//     public void createNewProjectForUser(@PathVariable String id, @RequestBody ProjectRequestDto projectDto){
//        service.createNewProjectForUser(id,projectDto);
//    }
//    @GetMapping("/my-projects/{id}")
//    public List<ProjectEntity> getAllProjectsFromUserById(@PathVariable String id){
//        return service.getAllProjectsFromUserById(id);
//    }
//    @DeleteMapping("/{userId}/delete-project/{projectId}")
//    public void deleteProjectForUserById(@PathVariable String userId,@PathVariable String projectId){
//        service.deleteProjectForUserById(userId,projectId);
//    }
//    @PutMapping("/{userId}/upd-project/{projectId}")
//    public void updateProjectForUser(@PathVariable String userId, @PathVariable String projectId, @RequestBody ProjectRequestDto projectDto){
//        service.updateProjectForUser(userId,projectId,projectDto);
//    }
}
