package es.eoi.services;

import es.eoi.model.Recetas;
import es.eoi.model.Usuario;
import es.eoi.repository.RecetasRespository;
import es.eoi.repository.UsuarioRepository;
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
