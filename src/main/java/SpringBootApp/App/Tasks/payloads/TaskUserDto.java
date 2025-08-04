package SpringBootApp.App.Tasks.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskUserDto {

    private String taskId;
    private String userId;
}
