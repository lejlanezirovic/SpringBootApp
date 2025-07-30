package SpringBootApp.App.services;

import SpringBootApp.App.payloads.ProjectRequestDto;
import SpringBootApp.App.payloads.UserProjectRequestDto;
import SpringBootApp.App.payloads.UserRequestDto;
import SpringBootApp.App.entities.ProjectEntity;
import SpringBootApp.App.entities.UserEntity;
import SpringBootApp.App.repository.ProjectRepository;
import SpringBootApp.App.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    //@Autowired
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    public List<UserEntity> getAllUsers(){
       List<UserEntity> users=new ArrayList<>();
       userRepository.findAll().forEach(users::add);
       return users;
    }
    public void addUser(UserRequestDto userDto){
        UserEntity user=new UserEntity();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        userRepository.save(user);
    }
    public void deleteUser(String id){
        UserEntity user=userRepository.findById(id)
                    .orElseThrow(()-> new EntityNotFoundException("User sa id "+ id+" nije pronadjen"));
        userRepository.deleteById(id);
    }
    public void addExistingProjectToUser(UserProjectRequestDto userProjectDto, String userid){
        UserEntity user=userRepository.findById(userid)
                .orElseThrow(()-> new EntityNotFoundException("nije pronadjen user sa id "+ userid));

        ProjectEntity project=projectRepository.findById(userProjectDto.getProjectId())
                .orElseThrow(()-> new EntityNotFoundException("nije pronadjen projekat sa id "+ userProjectDto.getProjectId()));

        user.getProjects().add(project);
        userRepository.save(user);
        project.getParticipants().add(user);
    }
    //create a new project
    //spasiti ga sa svim ostalim projektima
    //spasiti ga u userove projekte
    public void createNewProjectForUser(String userId, ProjectRequestDto projectDto){
        UserEntity user=userRepository.findById(userId)
                .orElseThrow(()-> new EntityNotFoundException("Nije pronadjen user sa id "+ userId));
        //tom useru trebamo dodati projekat
        //kao i u projectRepository
        ProjectEntity project=new ProjectEntity();
        project.setTitle(projectDto.getTitle());

        user.getProjects().add(project);
        project.getParticipants().add(user);
        projectRepository.save(project);
        userRepository.save(user);
    }
    public List<ProjectEntity> getAllProjectsFromUserById(String userId){
        List<ProjectEntity> projects=new ArrayList<>();
        UserEntity user=userRepository.findById(userId)
                .orElseThrow(()->new EntityNotFoundException("User nije pronadjen"));

        for(ProjectEntity p: user.getProjects()){
            projects.add(p);
        }
        return projects;
    }
    public void deleteProjectForUserById(String userId, String projectId){
        UserEntity user=userRepository.findById(userId)
                .orElseThrow(()->new EntityNotFoundException("User nije pronadjen"));
        ProjectEntity project=null;
        for(ProjectEntity p: user.getProjects()){
            if(p.getId().equals(projectId)){
                project=p;
                break;
            }
        }
        if(project!=null){
            user.getProjects().remove(project);
            userRepository.save(user);
        }
        else{
            new EntityNotFoundException("Projekat nije pronadjen");
        }
    }
    public void updateProjectForUser(String userId, String projectId, ProjectRequestDto projectRequestDto){
        UserEntity user=userRepository.findById(userId)
                .orElseThrow(()->new EntityNotFoundException("User nije pronadjen"));
        ProjectEntity project=projectRepository.findById(projectId)
                .orElseThrow(()-> new EntityNotFoundException("Projekat nije pronadjen"));

        if(!user.getProjects().contains(project)){
            throw new IllegalArgumentException("Projekat ne pripada korisniku");
        }
        project.setTitle(projectRequestDto.getTitle());
        projectRepository.save(project);
        userRepository.save(user);
    }
}
