package no.torrissen.motorcycles.repository;

import no.torrissen.motorcycles.model.Motorcycle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MotorcycleRepository extends JpaRepository<Motorcycle, Long> {
}
