package es.eoi.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDto {
    private long idCliente;
    private String nombre;

    private String apellidos;

    private String username;

    private String direccion;

    private String ciudad;

    private Date fechaCreacion;

    private Date fechaModif;

    private boolean BL;

}
