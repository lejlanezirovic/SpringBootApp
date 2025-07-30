package SpringBootApp.App.repository;

import SpringBootApp.App.entities.TaskEntity;
import com.sparktechcode.springjpasearch.repositories.SparkJpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends SparkJpaRepository<String, TaskEntity> {

}
