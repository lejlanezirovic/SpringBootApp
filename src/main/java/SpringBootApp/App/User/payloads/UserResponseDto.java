package SpringBootApp.App.User.payloads;

import SpringBootApp.App.Project.payloads.ProjectResponseDto;
import SpringBootApp.App.general.AuditDto;
import com.sparktechcode.springjpasearch.entities.IdHolder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserResponseDto extends AuditDto implements IdHolder<String> {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<ProjectResponseDto> projects;
}
