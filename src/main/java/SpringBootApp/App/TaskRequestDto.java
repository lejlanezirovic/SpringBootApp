package SpringBootApp.App;


import lombok.Data;

@Data
public class TaskRequestDto {

    private String title;
    private String description;
    private Status status;
    private String userId;

}
