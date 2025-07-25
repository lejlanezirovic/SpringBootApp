package SpringBootApp.App;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "tasks")
@EntityListeners(AuditingEntityListener.class)
@Data
public class TaskEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid",
                      strategy = "uuid")
    private String id;
    private String title;
    private String description;
    @CreatedDate
    private Instant createdAt;
    @LastModifiedDate
    private Instant updatedAt;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name="projectEntity_id")
    @JsonBackReference
    private ProjectEntity projectEntity;

    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonBackReference
    private UserEntity user;
    //mora biti @JsonBackReference, sprijecava serijalizaciju objekta
}
