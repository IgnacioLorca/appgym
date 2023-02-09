package es.eoi.dto;

import es.eoi.model.Usuario;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DatosBiometricosDto {
    private long idDatosBio;
    private float peso;
    private float altura;
    private float IMC;
    private Date fechaCreacion;
    private Date fechaModif;
    private boolean BL;
    private Usuario usuario;
}
