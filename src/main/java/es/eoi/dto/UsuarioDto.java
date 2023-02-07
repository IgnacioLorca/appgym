package es.eoi.dto;


import es.eoi.model.Role;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDto {
    private long id;
    private String nombre;
    private String apellidos;
    private String direccion;
    private String ciudad;
    private String provincia;
    private String email;
    private String username;
    private String fotoPerfil;

    private Set<Role> roles = new HashSet<>();
}
