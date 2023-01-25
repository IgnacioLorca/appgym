package es.eoi.services;

import es.eoi.model.Entrenamientos;
import es.eoi.model.Usuario;
import es.eoi.repository.EntrenadoresRepository;
import es.eoi.repository.EntrenamientosRepository;
import es.eoi.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntrenamientosSrvc extends AbstractBusinessServiceE<Entrenamientos, Integer, EntrenamientosRepository>{
    private final EntrenamientosRepository entrenamientosRepository;

    public EntrenamientosSrvc(EntrenamientosRepository entrenamientosRepository) {
        super(entrenamientosRepository);
        this.entrenamientosRepository = entrenamientosRepository;
    }

    public List<Entrenamientos> getEntrenamientos() {
        return entrenamientosRepository.findAll();
    }
}
