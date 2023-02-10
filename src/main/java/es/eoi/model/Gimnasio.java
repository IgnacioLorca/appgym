package es.eoi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "Gimnasio")
public class Gimnasio implements Serializable {

    @Id
    @Column (name = "id_gimnasio")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idGimnasio;
    @Column(name = "nombre", length = 50)
    private String nombre;
    @Column(name = "direccion", length = 50)
    private String direccion;
    @Column(name = "ciudad", length = 50)
    private String ciudad;
    @Column(name = "foto_gimnasio")
    private String fotoGimnasio;
    @Column(name = "fecha_creacion")
    private Date fechaCreacion;
    @Column(name = "fecha_modif")
    private Date fechaModif;
    @Column(name = "BL")
    private boolean BL;

    @OneToMany(mappedBy = "gimnasio")
    Set<Usuario> usuarios;
}
