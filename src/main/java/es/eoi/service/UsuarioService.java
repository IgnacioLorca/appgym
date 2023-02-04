package es.eoi.service;

import es.eoi.model.Usuario;
import es.eoi.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;


@Service
public class UsuarioService extends AbstractBusinessService<Usuario,Integer,
        UsuarioRepository>  {

    public UsuarioService(UsuarioRepository repo) {
        super(repo);
    }

    public Usuario guardar(Usuario usuario, String password){
        usuario.setPassword(password);
        Usuario entidadGuardada =  getRepo().save(usuario);
        return entidadGuardada;
    }

    public Usuario guardar(Usuario usuario){
        usuario.setPassword(usuario.getPassword());
        Usuario entidadGuardada =  getRepo().save(usuario);
        return entidadGuardada;
    }

    @Override
    public void  guardar(List<Usuario> lUsuario){
        Iterator<Usuario> it = lUsuario.iterator();
        while(it.hasNext()){
            Usuario usuario = it.next();
            usuario.setPassword(getRepo().getReferenceById((int) usuario.getIdUsuario()).getPassword());
            getRepo().save(usuario);
        }
    }

}
