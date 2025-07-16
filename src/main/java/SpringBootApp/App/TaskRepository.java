package SpringBootApp.App;

import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<TaskEntity,String> {

}
