package SpringBootApp.App.repository;

import SpringBootApp.App.entities.UserEntity;
import com.sparktechcode.springjpasearch.repositories.SparkJpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends SparkJpaRepository<String, UserEntity> {

    Optional<UserEntity> findByEmail(String email);
}
