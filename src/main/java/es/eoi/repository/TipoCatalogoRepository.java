package es.eoi.repository;

import es.eoi.model.TipoCatalogo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoCatalogoRepository extends JpaRepository<TipoCatalogo, Integer> {

    Page<TipoCatalogo> findAll(Pageable pageable);
}
