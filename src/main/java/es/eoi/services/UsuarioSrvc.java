package es.eoi.services;

import es.eoi.model.Usuario;
import es.eoi.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioSrvc extends AbstractBusinessServiceE<Usuario, Integer, UsuarioRepository> {


    private final UsuarioRepository usuarioRepository;

    public UsuarioSrvc(UsuarioRepository usuarioRepository, UsuarioRepository usuarioRepository1) {
        super(usuarioRepository);
        this.usuarioRepository = usuarioRepository1;
    }

    public List<Usuario> getUsuarios() {
        return usuarioRepository.findAll();
    }
}
