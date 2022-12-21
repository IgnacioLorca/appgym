package es.eoi.repository;


import es.eoi.model.Entrenamientos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntrenamientosRepository extends JpaRepository<Entrenamientos, Long> {
}
