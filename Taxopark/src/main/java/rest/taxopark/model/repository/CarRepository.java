package rest.taxopark.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rest.taxopark.model.entites.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
}
