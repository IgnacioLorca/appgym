package es.eoi.service;

import es.eoi.model.Role;
import es.eoi.repository.RoleRepository;
import es.eoi.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService extends AbstractBusinessService<Role, Integer, RoleRepository> {

    private final UsuarioRepository usuarioRepository;

    protected RoleService(RoleRepository repository, UsuarioRepository usuarioRepository) {
        super(repository);
        this.usuarioRepository = usuarioRepository;
    }
}
