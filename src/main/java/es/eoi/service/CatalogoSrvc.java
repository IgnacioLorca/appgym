package es.eoi.service;

import es.eoi.model.Catalogo;
import es.eoi.repository.CatalogoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogoSrvc extends AbstractBusinessServiceE<Catalogo, Integer, CatalogoRepository>{
    private final CatalogoRepository catalogoRepository;

    public CatalogoSrvc(CatalogoRepository catalogoRepository) {
        super(catalogoRepository);
        this.catalogoRepository = catalogoRepository;
    }

    public List<Catalogo> getCatalogo() {
        return catalogoRepository.findAll();
    }
}
