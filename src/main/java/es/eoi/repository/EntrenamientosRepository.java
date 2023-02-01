package es.eoi.repository;


import es.eoi.model.Entrenamientos;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntrenamientosRepository extends JpaRepository<Entrenamientos, Integer> {
}
