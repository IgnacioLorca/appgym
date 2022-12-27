package es.eoi.repository;

import es.eoi.model.Dietistas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DietistasRepository extends JpaRepository<Dietistas, Long> {
}
