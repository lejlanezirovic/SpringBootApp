package SpringBootApp.App.Tasks.mappers;

import SpringBootApp.App.Tasks.entities.TaskEntity;
import SpringBootApp.App.Tasks.payloads.TaskRequestDto;
import SpringBootApp.App.Tasks.payloads.TaskResponseDto;
import com.sparktechcode.springcrud.mappers.CrudMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaskMapper extends CrudMapper<String, TaskRequestDto, TaskEntity, TaskResponseDto> {

    @Mapping(source = "user.firstName", target = "firstName")
    @Mapping(source = "user.lastName", target = "lastName")
    TaskResponseDto toDto(TaskEntity taskEntity);
}
