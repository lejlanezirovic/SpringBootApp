package SpringBootApp.App;

public class TaskMapper {

    public static TaskEntity toEntity (TaskRequestDto requestDto){
        TaskEntity task=new TaskEntity();
        task.setTitle(requestDto.getTitle());
        task.setDescription(requestDto.getDescription());
        task.setStatus(requestDto.getStatus());
        return task;
    }

    public static  TaskResponseDto toDto(TaskEntity task){
        TaskResponseDto response=new TaskResponseDto();
        response.setId(task.getId());
        response.setTitle(task.getTitle());
        response.setStatus(task.getStatus());
        response.setDescription(task.getDescription());
        response.setUpdatedAt(task.getUpdatedAt());
        return response;
    }
}
