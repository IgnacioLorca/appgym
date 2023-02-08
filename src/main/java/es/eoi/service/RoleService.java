package es.eoi.service;

import es.eoi.dto.RoleDto;
import es.eoi.model.Role;
import es.eoi.model.Usuario;
import es.eoi.repository.RoleRepository;
import es.eoi.repository.UsuarioRepository;
import es.eoi.service.mapper.RoleServiceMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class RoleService extends AbstractBusinessService<Role, Integer, RoleDto, RoleRepository, RoleServiceMapper> {

    private final UsuarioRepository usuarioRepository;

    protected RoleService(RoleRepository repository, RoleServiceMapper serviceMapper, UsuarioRepository usuarioRepository) {
        super(repository, serviceMapper);
        this.usuarioRepository = usuarioRepository;
    }

}
