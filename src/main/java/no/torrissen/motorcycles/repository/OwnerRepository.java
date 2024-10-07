package no.torrissen.motorcycles.repository;

import no.torrissen.motorcycles.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
}
