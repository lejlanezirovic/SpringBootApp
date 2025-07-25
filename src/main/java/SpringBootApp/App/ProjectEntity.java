package SpringBootApp.App;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="projects")
@EntityListeners(AuditingEntityListener.class)
@Data
public class ProjectEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid", strategy = "uuid")
    private String id;
    private String title;
    @CreatedDate
    private Instant createdAt;
    @LastModifiedDate
    private Instant updatedAt;

    @OneToMany(mappedBy = "projectEntity", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<TaskEntity> tasks;

    @ManyToMany(mappedBy = "projects")
    @JsonBackReference
    private List<UserEntity> participants=new ArrayList<>();
}
