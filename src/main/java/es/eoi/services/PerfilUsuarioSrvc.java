package es.eoi.services;

import es.eoi.model.PerfilUsuario;
import es.eoi.model.Usuario;
import es.eoi.repository.PerfilUsuarioRepository;
import es.eoi.repository.UsuarioRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
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
