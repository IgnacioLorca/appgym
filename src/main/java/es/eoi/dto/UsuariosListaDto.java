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

    List<UsuarioDto> usuarioDtos;

    public void anadirUsuarios(UsuarioDto usuarioDto){
        this.usuarioDtos.add(usuarioDto);
    }
}
