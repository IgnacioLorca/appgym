package es.eoi.repository;

import es.eoi.model.MaterialReservas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialReservasRepository extends JpaRepository<MaterialReservas,Long> {
}
