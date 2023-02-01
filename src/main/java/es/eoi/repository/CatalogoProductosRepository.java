package es.eoi.repository;

import es.eoi.model.CatalogoProductos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogoProductosRepository extends JpaRepository<CatalogoProductos,Integer> {
}
