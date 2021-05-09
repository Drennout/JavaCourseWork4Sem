package rest.taxopark.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rest.taxopark.model.entites.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
