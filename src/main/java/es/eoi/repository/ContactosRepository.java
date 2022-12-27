package es.eoi.repository;

import es.eoi.model.ListaContactos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactosRepository extends JpaRepository<ListaContactos,Long> {
}