package SpringBootApp.App.Tasks.payloads;

import SpringBootApp.App.general.Status;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequestDto {

    private String title;
    private String description;
    private Status status;
    private String userId;

}
