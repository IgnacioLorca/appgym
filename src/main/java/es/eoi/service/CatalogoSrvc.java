package es.eoi.service;

import es.eoi.dto.CatalogoDto;
import es.eoi.model.Catalogo;
import es.eoi.repository.CatalogoRepository;
import es.eoi.service.mapper.CatalogoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogoSrvc extends AbstractBusinessService<Catalogo, Integer, CatalogoDto, CatalogoRepository, CatalogoMapper>{

    @Autowired
    private CatalogoRepository catalogoRepository;

    public CatalogoSrvc(CatalogoRepository repo, CatalogoMapper serviceMapper) {
        super(repo, serviceMapper);
    }

    public List<Catalogo> getCatalogo() {
        return catalogoRepository.findAll();
    }
}
