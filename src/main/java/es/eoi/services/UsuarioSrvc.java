package es.eoi.services;

import es.eoi.model.Usuario;
import es.eoi.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

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

    public Usuario guardar(Optional<Usuario> usuario, String password){
        System.out.println("usuario:" + usuario.get().getNombre() );
        //Traduzco del dto con datos de entrada a la entidad
        final Usuario entidad = (Usuario) getUsuarios();
        System.out.println("Entidad:" + getUsuarios());
        entidad.setPassword(password);
        System.out.println("Entidad:" + ((Usuario) getUsuarios()).getPassword() );
        //Guardo el la base de datos
        Usuario entidadGuardada =  getRepo().save(entidad);
        //Traducir la entidad a DTO para devolver el DTO
        return entidadGuardada;
    }
    public Usuario guardar(Usuario usuarioPsw){
        System.out.println("usuario:" + usuarioPsw.getNombre() );
        //Traduzco del dto con datos de entrada a la entidad
        final Usuario usuario = (Usuario) getUsuarios();
        System.out.println("Entidad:" + usuarioPsw.getNombre() );
        //Guardo el la base de datos
        Usuario usuarioGuardado =  getRepo().save(usuario);
        //Traducir la entidad a DTO para devolver el DTO
        return usuarioGuardado;
    }
    //Método para guardar una lista de usuarios
    //La entrada es una lista de DTO ( que viene de la pantalla)
    //La respuesta tipo void
    @Override
    public void  guardar(List<Usuario> usuarioList){
        Iterator<Usuario> it = usuarioList.iterator();
        // mientras al iterador queda proximo juego
        while(it.hasNext()){
            //Obtenemos la password de a entidad
            //Datos del usuario
            Usuario usuario = getUsuarios().iterator().next();
            usuario.setPassword(getRepo().getReferenceById((int) usuario.getIdUsuario()).getPassword());
            getRepo().save(usuario);
        }
    }
}
