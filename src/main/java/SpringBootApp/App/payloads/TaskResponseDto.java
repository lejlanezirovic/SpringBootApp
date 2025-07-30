package SpringBootApp.App.payloads;


import SpringBootApp.App.entities.Status;
import lombok.Data;

import java.time.Instant;

@Data
public class TaskResponseDto {
    private String id;
    private String title;
    private String description;
    private Status status;
    private Instant updatedAt;
    private String firstName;
    private String lastName;

}
