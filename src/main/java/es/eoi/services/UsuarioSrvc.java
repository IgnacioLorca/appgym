package es.eoi.services;

import es.eoi.model.Usuario;
import es.eoi.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioSrvc {
    private final UsuarioRepository usurep;

    public UsuarioSrvc(UsuarioRepository usuarioRepository) {
        this.usurep = usuarioRepository;
    }

    public List<Usuario> getUsuarios() {
        return usurep.findAll();
    }
}
