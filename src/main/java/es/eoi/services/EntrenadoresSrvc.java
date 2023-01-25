package es.eoi.services;

import es.eoi.model.Entrenadores;
import es.eoi.model.Usuario;
import es.eoi.repository.EntrenadoresRepository;
import es.eoi.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntrenadoresSrvc extends AbstractBusinessServiceE<Entrenadores, Integer, EntrenadoresRepository>{
    private final EntrenadoresRepository entrenadoresRepository;

    public EntrenadoresSrvc(EntrenadoresRepository entrenadoresRepository) {
        super(entrenadoresRepository);
        this.entrenadoresRepository = entrenadoresRepository;
    }

    public List<Entrenadores> getEntrenadores() {
        return entrenadoresRepository.findAll();
    }
}
