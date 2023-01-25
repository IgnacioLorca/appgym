package es.eoi.repository;

import es.eoi.model.ClasesPrecio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClasesPrecioRepository extends JpaRepository<ClasesPrecio,Integer> {
}
