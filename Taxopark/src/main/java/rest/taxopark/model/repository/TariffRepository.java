package rest.taxopark.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rest.taxopark.model.entites.Tariff;

@Repository
public interface TariffRepository extends JpaRepository<Tariff, Long> {
}
