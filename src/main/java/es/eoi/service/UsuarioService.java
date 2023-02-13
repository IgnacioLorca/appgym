package es.eoi.service;

import es.eoi.dto.UsuarioDto;
import es.eoi.dto.UsuarioDtoPsw;
import es.eoi.model.Usuario;
import es.eoi.repository.UsuarioRepository;
import es.eoi.service.mapper.UsuarioMapper;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class UsuarioService extends AbstractBusinessService<Usuario,Integer, UsuarioDto,
        UsuarioRepository, UsuarioMapper>   {

    public UsuarioService(UsuarioRepository repo, UsuarioMapper serviceMapper) {
        super(repo, serviceMapper);
    }

    public UsuarioDto guardar(UsuarioDto usuarioDto, String password){
        System.out.println("usuarioDto:" +usuarioDto.getUsername() );
        // Traduzo datos del DTO a Entidad
        final Usuario entidad = getMapper().toEntity(usuarioDto);
        System.out.println("Entidad:" +entidad.getUsername() );
        entidad.setPassword(password);
        System.out.println("Entidad:" +entidad.getPassword() );
        // Guardo en la base de datos
        Usuario entidadGuardada =  getRepo().save(entidad);
        // Traduzco la entidad a DTO
        return getMapper().toDto(entidadGuardada);
    }

    public UsuarioDto guardar(UsuarioDtoPsw usuarioDtoPsw){
        System.out.println("usuarioDto:" +usuarioDtoPsw.getUsername() );
        // Traduzo datos del DTO a Entidad
        final Usuario entidad = getMapper().toEntityPsw(usuarioDtoPsw);
        System.out.println("Entidad:" +entidad.getUsername() );
        // Guardo el la base de datos
        Usuario entidadGuardada =  getRepo().save(entidad);
        // Traduzco la entidad a DTO
        return getMapper().toDto(entidadGuardada);
    }

    @Override
    public void  guardar(List<UsuarioDto> lUsuarioDto){
        Iterator<UsuarioDto> it = lUsuarioDto.iterator();
        while(it.hasNext()){
            Usuario usuario = getMapper().toEntity(it.next());
            usuario.setPassword(getRepo().getReferenceById((int) usuario.getIdUsuario()).getPassword());
            getRepo().save(usuario);
        }
    }

}
