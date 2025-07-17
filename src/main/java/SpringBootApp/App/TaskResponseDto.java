package SpringBootApp.App;


import lombok.Data;

import java.time.Instant;
import java.time.LocalDate;

@Data
public class TaskResponseDto {
    private String id;
    private String title;
    private String description;
    private Status status;
    private Instant updatedAt;


}
