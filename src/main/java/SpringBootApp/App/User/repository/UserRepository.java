package SpringBootApp.App.User.repository;

import SpringBootApp.App.User.entities.UserEntity;
import com.sparktechcode.springjpasearch.repositories.SparkJpaRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends SparkJpaRepository<String, UserEntity> {

    Optional<UserEntity> findByEmail(String email);
}
