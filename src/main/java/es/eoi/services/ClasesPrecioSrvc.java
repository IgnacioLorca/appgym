package es.eoi.services;

import es.eoi.model.ClasesPrecio;
import es.eoi.model.Usuario;
import es.eoi.repository.ClasesPrecioRepository;
import es.eoi.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClasesPrecioSrvc extends AbstractBusinessServiceE<ClasesPrecio, Integer, ClasesPrecioRepository>{
    private final ClasesPrecioRepository clasesPrecioRepository;

    public ClasesPrecioSrvc(ClasesPrecioRepository clasesPrecioRepository) {
        super(clasesPrecioRepository);
        this.clasesPrecioRepository = clasesPrecioRepository;
    }

    public List<ClasesPrecio> getClasesPrecio() {
        return clasesPrecioRepository.findAll();
    }
}
