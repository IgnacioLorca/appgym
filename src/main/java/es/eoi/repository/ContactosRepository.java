package es.eoi.repository;

import es.eoi.model.Contactos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactosRepository extends JpaRepository<Contactos,Integer> {
}