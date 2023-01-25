package es.eoi.repository;

import es.eoi.model.Recetas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecetasRespository extends JpaRepository<Recetas, Integer> {
}
