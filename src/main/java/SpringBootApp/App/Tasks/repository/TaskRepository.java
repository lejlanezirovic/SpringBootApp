package SpringBootApp.App.Tasks.repository;

import SpringBootApp.App.Tasks.entities.TaskEntity;
import com.sparktechcode.springjpasearch.repositories.SparkJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends SparkJpaRepository<String, TaskEntity> {

}
