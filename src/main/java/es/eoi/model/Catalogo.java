package es.eoi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "Catalogo")
public class Catalogo {
    @Id
    @Column(name = "id_catalogo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCatalogo;
    @Column (name ="nombre",length = 50)
    private String nombre;
    @Column (name ="descripcion",length = 100)
    private String descripcion;
    @Column(name = "precio")
    private float precio;
    @Column(name = "imagenCatalogo")
    private String imagenCatalogo;
    @Column (name = "fecha_creacion")
    private Date fechaCreacion;
    @Column (name = "fecha_modif")
    private Date fechaModif;
    @Column (name = "BL")
    private boolean BL;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<TipoCatalogo> tipoCatalogo;
}
