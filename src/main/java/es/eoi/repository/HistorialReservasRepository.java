package es.eoi.repository;

import es.eoi.model.HistorialReservas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistorialReservasRepository extends JpaRepository<HistorialReservas,Long> {
}
