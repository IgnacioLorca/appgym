package es.eoi.services;

import es.eoi.model.CatalogoProductos;
import es.eoi.model.Usuario;
import es.eoi.repository.CatalogoProductosRepository;
import es.eoi.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogoProductosSrvc extends AbstractBusinessServiceE<CatalogoProductos, Integer, CatalogoProductosRepository>{
    private final CatalogoProductosRepository catalogoProductosRepository;

    public CatalogoProductosSrvc(CatalogoProductosRepository catalogoProductosRepository) {
        super(catalogoProductosRepository);
        this.catalogoProductosRepository = catalogoProductosRepository;
    }

    public List<CatalogoProductos> getCatalogoProductos() {
        return catalogoProductosRepository.findAll();
    }
}
