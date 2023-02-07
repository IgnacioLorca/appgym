package es.eoi.service;


import es.eoi.dto.UsuarioDto;
import es.eoi.dto.UsuarioDtoPsw;
import es.eoi.model.Usuario;
import es.eoi.repository.UsuarioRepository;
import es.eoi.service.mapper.UsuarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UsuarioService extends AbstractBusinessService<Usuario,Integer, UsuarioDto,
        UsuarioRepository, UsuarioMapper> implements UserDetailsService {
    //Acceso a los datos de la bbdd

    public UsuarioService(UsuarioRepository repo, UsuarioMapper serviceMapper) {
        super(repo, serviceMapper);
    }


    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    public UsuarioDto guardar(UsuarioDto usuarioDto, String password){
        System.out.println("usuarioDto:" +usuarioDto.getUsername()  );
        //Traduzco del dto con datos de entrada a la entidad
        final Usuario entidad = getMapper().toEntity(usuarioDto);
        System.out.println("Entidad:" +entidad.getNombre() );
        entidad.setPassword(password);
        System.out.println("Entidad:" +entidad.getPassword() );
        //Guardo el la base de datos
        Usuario entidadGuardada =  getRepo().save(entidad);
        //Traducir la entidad a DTO para devolver el DTO
        return getMapper().toDto(entidadGuardada);
    }
    public UsuarioDto guardar(UsuarioDtoPsw usuarioDtoPsw){
        System.out.println("usuarioDto:" +usuarioDtoPsw.getUsername()  );
        //Traduzco del dto con datos de entrada a la entidad
        final Usuario entidad = getMapper().toEntityPsw(usuarioDtoPsw);
        System.out.println("Entidad:" +entidad.getNombre() );
        //Guardo el la base de datos
        Usuario entidadGuardada =  getRepo().save(entidad);
        //Traducir la entidad a DTO para devolver el DTO
        return getMapper().toDto(entidadGuardada);
    }
    //Método para guardar una lista de usuarios
    //La entrada es una lista de DTO ( que viene de la pantalla)
    //La respuesta tipo void
    @Override
    public void  guardar(List<UsuarioDto> lUsuarioDto){
        Iterator<UsuarioDto> it = lUsuarioDto.iterator();

        // mientras al iterador queda proximo juego
        while(it.hasNext()){
            //Obtenemos la password de a entidad
            //Datos del usuario
            Usuario usuario = getMapper().toEntity(it.next());
            usuario.setPassword(getRepo().getReferenceById((int) usuario.getIdUsuario()).getPassword());
            getRepo().save(usuario);
        }
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> optUsu = usuarioRepository.findUsuarioByEmail(username);
        if(optUsu.isEmpty())
            throw new UsernameNotFoundException("El usuario con el nombre: " + username +" no existe.");
        else {
            Usuario user = optUsu.get();
            return new org.springframework.security.core.userdetails.User(
                    user.getEmail(),
                    user.getPassword(),
                    user.getRoles()
                            .stream()
                            .map(r-> new SimpleGrantedAuthority(r.getNombreRole()))
                            .collect(Collectors.toSet())
            );
        }
    }
}

