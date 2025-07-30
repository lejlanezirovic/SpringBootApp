package SpringBootApp.App.controllers;

import SpringBootApp.App.entities.ProjectEntity;
import SpringBootApp.App.entities.UserEntity;
import SpringBootApp.App.payloads.ProjectRequestDto;
import SpringBootApp.App.payloads.UserProjectRequestDto;
import SpringBootApp.App.payloads.UserRequestDto;
import SpringBootApp.App.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserEntity> getAllUsers(){
        return userService.getAllUsers();
    }
    @PostMapping
    public void addUser(@RequestBody UserRequestDto userDto){
        userService.addUser(userDto);
    }
    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable String id){
        userService.deleteUser(id);
    }
    @PostMapping("/{id}/add-project")
    public void addExistingProjectToUser(@RequestBody UserProjectRequestDto userProjectDto, @PathVariable String id){
        userService.addExistingProjectToUser(userProjectDto, id);
    }
    @PostMapping("/create-new-project/{id}")
     public void createNewProjectForUser(@PathVariable String id, @RequestBody ProjectRequestDto projectDto){
        userService.createNewProjectForUser(id,projectDto);
    }
    @GetMapping("/my-projects/{id}")
    public List<ProjectEntity> getAllProjectsFromUserById(@PathVariable String id){
        return userService.getAllProjectsFromUserById(id);
    }
    @DeleteMapping("/{userId}/delete-project/{projectId}")
    public void deleteProjectForUserById(@PathVariable String userId,@PathVariable String projectId){
        userService.deleteProjectForUserById(userId,projectId);
    }
    @PutMapping("/{userId}/upd-project/{projectId}")
    public void updateProjectForUser(@PathVariable String userId, @PathVariable String projectId, @RequestBody ProjectRequestDto projectDto){
        userService.updateProjectForUser(userId,projectId,projectDto);
    }
}
