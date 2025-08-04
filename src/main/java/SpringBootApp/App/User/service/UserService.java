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
}
