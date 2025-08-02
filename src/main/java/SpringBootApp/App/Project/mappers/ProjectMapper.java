package SpringBootApp.App.Project.mappers;

import SpringBootApp.App.Project.entities.ProjectEntity;
import SpringBootApp.App.Project.payloads.ProjectRequestDto;
import SpringBootApp.App.Project.payloads.ProjectResponseDto;
import com.sparktechcode.springcrud.mappers.CrudMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProjectMapper extends CrudMapper<String, ProjectRequestDto, ProjectEntity, ProjectResponseDto> {

    @Mapping(source="created", target="created")
    @Mapping(source="updated", target="updated")
    @Mapping(source = "tasks", target = "tasks")
    ProjectResponseDto toDto(ProjectEntity projectEntity);

}
