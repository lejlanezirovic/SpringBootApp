package SpringBootApp.App.Tasks.entities;

import SpringBootApp.App.Project.entities.ProjectEntity;
import SpringBootApp.App.general.Status;
import SpringBootApp.App.User.entities.UserEntity;
import com.sparktechcode.springcrud.entities.AutoIdBaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "tasks")
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true, callSuper = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class TaskEntity extends AutoIdBaseEntity<String> {

    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name="projectEntity_id", insertable = false,updatable = false)
    private String projectId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="projectEntity_id")
    private ProjectEntity projectEntity;

    @Column(name = "user_id", insertable = false,updatable = false)
    private String userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private UserEntity user;

}
