package es.eoi.dto;

import es.eoi.model.TipoCatalogo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CatalogoDto {
    private long idCatalogo;
    private String nombre;
    private String descripcion;
    private float precio;
    private float imagenCatalogo;
    private Date fechaCreacion;
    private Date fechaModif;
    private boolean BL;


    private Set<TipoCatalogo> tipoCatalogo;
}
