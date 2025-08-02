package SpringBootApp.App.User.service;

import SpringBootApp.App.Project.payloads.ProjectRequestDto;
import SpringBootApp.App.User.payloads.UserProjectRequestDto;
import SpringBootApp.App.Project.entities.ProjectEntity;
import SpringBootApp.App.User.entities.UserEntity;
import SpringBootApp.App.Project.repository.ProjectRepository;
import SpringBootApp.App.User.repository.UserRepository;
import com.sparktechcode.springcrud.services.CrudService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Getter
@Service
@RequiredArgsConstructor
public class UserService implements CrudService<String, UserEntity> {

    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final EntityManager entityManager;

    public void addExistingProjectToUser(UserProjectRequestDto userProjectDto, String userid){
        UserEntity user=userRepository.findById(userid)
                .orElseThrow(()-> new EntityNotFoundException("nije pronadjen user sa id "+ userid));

        ProjectEntity project=projectRepository.findById(userProjectDto.getProjectId())
                .orElseThrow(()-> new EntityNotFoundException("nije pronadjen projekat sa id "+ userProjectDto.getProjectId()));

        user.getProjects().add(project);
        userRepository.save(user);
        project.getParticipants().add(user);
    }

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
