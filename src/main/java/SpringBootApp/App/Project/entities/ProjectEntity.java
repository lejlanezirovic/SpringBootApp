package SpringBootApp.App.Project.entities;


import SpringBootApp.App.Tasks.entities.TaskEntity;
import SpringBootApp.App.User.entities.UserEntity;
import com.sparktechcode.springcrud.entities.AutoIdBaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="projects")
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true, callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class ProjectEntity extends AutoIdBaseEntity<String> {

    private String title;

    @OneToMany(mappedBy = "projectEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TaskEntity> tasks;

    @ManyToMany(mappedBy = "projects", fetch = FetchType.LAZY)
    private List<UserEntity> participants=new ArrayList<>();
}
