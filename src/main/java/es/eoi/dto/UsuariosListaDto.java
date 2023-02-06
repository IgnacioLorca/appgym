package es.eoi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsuariosListaDto {

    //Necesito una lista de usuarioDTO a manejar para la edición multiple
    List<UsuarioDto> usuarioDtos;

    //Metodo para añadir registros a la lista
    public void anadirUsuarios(UsuarioDto usuarioDto){
        this.usuarioDtos.add(usuarioDto);
    }
}
