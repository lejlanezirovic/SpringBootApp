package SpringBootApp.App.User.mappers;

import SpringBootApp.App.Project.entities.ProjectEntity;
import SpringBootApp.App.User.entities.UserEntity;
import SpringBootApp.App.Project.payloads.ProjectResponseDto;
import SpringBootApp.App.User.payloads.UserRequestDto;
import SpringBootApp.App.User.payloads.UserResponseDto;
import com.sparktechcode.springcrud.mappers.CrudMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper extends CrudMapper<String, UserRequestDto, UserEntity, UserResponseDto> {
    @Mapping(source="projects", target="projects")
    UserResponseDto toDto(UserEntity userEntity);

    List<ProjectResponseDto> toProjectDtoList(List<ProjectEntity> projects);
}
