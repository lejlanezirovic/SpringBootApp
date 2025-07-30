package SpringBootApp.App.payloads;


import SpringBootApp.App.entities.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequestDto {

    private String title;
    private String description;
    private Status status;
    private String userId;

}
