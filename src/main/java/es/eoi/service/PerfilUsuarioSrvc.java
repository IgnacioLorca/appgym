package es.eoi.service;

import es.eoi.model.PerfilUsuario;
import es.eoi.repository.PerfilUsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerfilUsuarioSrvc extends AbstractBusinessServiceE<PerfilUsuario, Integer, PerfilUsuarioRepository>{
    private final PerfilUsuarioRepository perfilUsuarioRepository;

    public PerfilUsuarioSrvc(PerfilUsuarioRepository perfilUsuarioRepository) {
        super(perfilUsuarioRepository);
        this.perfilUsuarioRepository = perfilUsuarioRepository;
    }

    public List<PerfilUsuario> getPerfilUsuario() {
        return perfilUsuarioRepository.findAll();
    }
}
