package SpringBootApp.App.Project.repository;

import SpringBootApp.App.Project.entities.ProjectEntity;
import com.sparktechcode.springjpasearch.repositories.SparkJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends SparkJpaRepository<String, ProjectEntity> {

}
