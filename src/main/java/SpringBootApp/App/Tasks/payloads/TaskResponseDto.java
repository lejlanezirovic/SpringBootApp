package SpringBootApp.App.Tasks.payloads;


import SpringBootApp.App.general.Status;
import SpringBootApp.App.general.AuditDto;
import com.sparktechcode.springjpasearch.entities.IdHolder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskResponseDto extends AuditDto implements IdHolder<String> {
    private String id;
    private String title;
    private String description;
    private Status status;
    private String firstName;
    private String lastName;

}
