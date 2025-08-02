package SpringBootApp.App.Project.payloads;

import SpringBootApp.App.general.AuditDto;
import SpringBootApp.App.Tasks.payloads.TaskResponseDto;
import com.sparktechcode.springjpasearch.entities.IdHolder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProjectResponseDto extends AuditDto implements IdHolder<String> {
    private String id;
    private String title;
    private List<TaskResponseDto> tasks;
    //created i updated iz AuditDto
}
