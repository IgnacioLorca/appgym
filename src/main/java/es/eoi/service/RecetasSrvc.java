package es.eoi.service;

import es.eoi.model.Recetas;
import es.eoi.repository.RecetasRespository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecetasSrvc extends AbstractBusinessServiceE<Recetas, Integer, RecetasRespository>{
    private final RecetasRespository recetasRespository;

    public RecetasSrvc(RecetasRespository recetasRespository) {
        super(recetasRespository);
        this.recetasRespository = recetasRespository;
    }

    public List<Recetas> getRecetas() {
        return recetasRespository.findAll();
    }
}
