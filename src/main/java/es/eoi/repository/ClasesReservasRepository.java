package es.eoi.repository;

import es.eoi.model.ClasesReservas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClasesReservasRepository extends JpaRepository<ClasesReservas,Long> {
}
