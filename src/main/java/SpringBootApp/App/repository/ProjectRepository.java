package SpringBootApp.App.repository;

import SpringBootApp.App.entities.ProjectEntity;
import com.sparktechcode.springjpasearch.repositories.SparkJpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends SparkJpaRepository<String, ProjectEntity> {

}
